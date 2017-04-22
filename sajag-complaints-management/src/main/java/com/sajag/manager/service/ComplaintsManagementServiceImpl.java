package com.sajag.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sajag.manager.dto.ComplaintsDetails;
import com.sajag.manager.dto.FullComplaintDetails;
import com.sajag.manager.persistence.Complaints;
import com.sajag.manager.persistence.Images;
import com.sajag.manager.persistence.UserComplaintsKey;
import com.sajag.manager.persistence.mapper.ImagesMapper;
import com.sajag.manager.persistence.mapper.UserComplaintsMapper;
import com.sajag.manager.repository.ComplaintsRepository;

@Service
public class ComplaintsManagementServiceImpl implements
		ComplaintsManagementService {

	@Autowired
	private ComplaintsRepository complaintsRepository;
	
	@Autowired
	private ImagesMapper imagesMapper;
	
	@Autowired
	UserComplaintsMapper userComplaintsMapper;
	
	@Override
	public int addComplaint(ComplaintsDetails complaintsDetails,Long userMobileNo) {
		complaintsRepository.insertComplaint(populateComplaint(complaintsDetails));
		
		List<MultipartFile> complaintImages = complaintsDetails.getImages();
		
		List<Complaints> createdComplaint = this.selectComplaintByCriteria(complaintsDetails);
		
		int createdComplaintId = createdComplaint.get(0).getComplaintId();
		
		complaintsRepository.mapImagesWithComplaint(complaintImages, createdComplaintId); 
		
		UserComplaintsKey userComplaintsKey = new UserComplaintsKey();
		userComplaintsKey.setComplaintId(createdComplaintId);
		userComplaintsKey.setUserMobile(userMobileNo);
		
		return userComplaintsMapper.insert(userComplaintsKey);
		
	}
	
	private Complaints populateComplaint(ComplaintsDetails complaintsDetails) {
		Complaints complaints = new Complaints();
		complaints.setComments(complaintsDetails.getComments());
		complaints.setComplaintStatus(complaintsDetails.getComplaintStatus());
		complaints.setCreateDate(complaintsDetails.getCreationTime());
		complaints.setLastUpdateDate(complaintsDetails.getLastUpdateTime());
		complaints.setDepartmentId(complaintsDetails.getDepartmentId());
			
		return complaints;
	}

	@Override
	public List<Complaints> selectComplaintByCriteria(ComplaintsDetails complaintsDetails) {
		return complaintsRepository.selectComplaintByCriteria(complaintsDetails);
	}

	@Override
	public int mapImagesWithComplaint(List<MultipartFile> images) {
		return 0;
	}

	@Override
	public List<FullComplaintDetails> getAllComplaints() {
		return complaintsRepository.getAllComplaints();
	}

	@Override
	public List<FullComplaintDetails> getAllUserComplaints(Long userMobileNo) {
		return complaintsRepository.getAllComplaints(userMobileNo);
	}

	@Override
	public List<byte[]> fetchComplaintImages(Integer complaintId) {
		List<Images> complaintImages = complaintsRepository.getComplaintImages(complaintId);
		return this.populateImagesByteArraysList(complaintImages);
	}
	
	private List<byte [] > populateImagesByteArraysList(List<Images> complaintImages){
		List<byte [] > imagesByteArraysList = new ArrayList<byte[]>();
		for (Images image: complaintImages) {
			imagesByteArraysList.add(image.getImageData());
		}
		return imagesByteArraysList;
	}

	@Override
	public ComplaintsDetails fetchComplaintDetails(Integer complaintId) {
		return complaintsRepository.fetchComplaintDetails(complaintId);
	}

	@Override
	public int updateComplaint(Integer complaintId, String status) {
		return complaintsRepository.updateComplaint(complaintId, status);
	}
}
