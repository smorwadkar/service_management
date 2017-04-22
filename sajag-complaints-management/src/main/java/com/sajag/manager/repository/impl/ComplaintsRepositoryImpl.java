package com.sajag.manager.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.sajag.manager.dto.ComplaintsDetails;
import com.sajag.manager.dto.FullComplaintDetails;
import com.sajag.manager.persistence.ComplaintImagesExample;
import com.sajag.manager.persistence.ComplaintImagesKey;
import com.sajag.manager.persistence.Complaints;
import com.sajag.manager.persistence.ComplaintsExample;
import com.sajag.manager.persistence.Images;
import com.sajag.manager.persistence.UserComplaintsKey;
import com.sajag.manager.persistence.mapper.ComplaintImagesMapper;
import com.sajag.manager.persistence.mapper.ComplaintsMapper;
import com.sajag.manager.persistence.mapper.ImagesMapper;
import com.sajag.manager.persistence.mapper.UserComplaintsMapper;
import com.sajag.manager.repository.ComplaintsRepository;

@Repository
public class ComplaintsRepositoryImpl implements ComplaintsRepository {

	@Autowired
	private ComplaintsMapper complaintsMapper;

	@Autowired
	private ComplaintImagesMapper complaintImagesMapper;

	@Autowired
	private ImagesMapper imagesMapper;

	@Autowired
	UserComplaintsMapper userComplaintsMapper;

	@Override
	public int insertComplaint(Complaints complaints) {
		Integer pKey = 0;
		pKey = complaintsMapper.insert(complaints);
		return pKey;
	}

	@Override
	public List<Complaints> selectComplaintByCriteria(
			ComplaintsDetails complaintsDetails) {

		ComplaintsExample complaintsExample = new ComplaintsExample();
		complaintsExample
				.createCriteria()
				.andCommentsEqualTo(complaintsDetails.getComments())
				.andComplaintStatusEqualTo(
						complaintsDetails.getComplaintStatus())
				.andCreateDateEqualTo(complaintsDetails.getCreationTime())
				.andLastUpdateDateEqualTo(complaintsDetails.getLastUpdateTime());

		return complaintsMapper.selectByExample(complaintsExample);
	}

	@Override
	public int mapImagesWithComplaint(List<MultipartFile> images,
			Integer complaintId) {

		// First insert Images
		for (MultipartFile multipartFile : images) {
			ComplaintImagesKey complaintImagesKey = new ComplaintImagesKey();
			complaintImagesKey.setComplaintId(complaintId);

			Images image = new Images();
			try {
				image.setImageData(multipartFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			imagesMapper.insertWithKey(image);
//			Images insertedImage = imagesMapper.selectImageByImageData(image);
			complaintImagesKey.setImageId(image.getImageId());
			complaintImagesMapper.insert(complaintImagesKey);
		}

		return 0;
	}

	/**
	 * 
	 * Get all complaints
	 */
	@Override
	public List<FullComplaintDetails> getAllComplaints() {
		List<UserComplaintsKey> allComplaints = userComplaintsMapper
				.getAllComplaints();

		List<FullComplaintDetails> allComplaintDetails = new ArrayList<>();

		for (UserComplaintsKey userComplaintsKey : allComplaints) {
			FullComplaintDetails fullComplaintDetail = new FullComplaintDetails();
			fullComplaintDetail.setUserMobileNo(userComplaintsKey
					.getUserMobile());
			Complaints complaint = complaintsMapper
					.selectByPrimaryKey(userComplaintsKey.getComplaintId());
			ComplaintsDetails complaintDetail = populateComplaintDetailsFromComplaint(complaint);
			fullComplaintDetail.setComplaintsDetails(complaintDetail);
			allComplaintDetails.add(fullComplaintDetail);
		}

		return allComplaintDetails;
	}

	/**
	 * Get all complaints for a user
	 */
	@Override
	public List<FullComplaintDetails> getAllComplaints(Long userMobileNo) {
		List<UserComplaintsKey> allComplaints = userComplaintsMapper
				.getAllUserComplaints(userMobileNo);

		List<FullComplaintDetails> allComplaintDetails = new ArrayList<>();

		for (UserComplaintsKey userComplaintsKey : allComplaints) {
			FullComplaintDetails fullComplaintDetail = new FullComplaintDetails();
			fullComplaintDetail.setUserMobileNo(userComplaintsKey
					.getUserMobile());
			Complaints complaint = complaintsMapper
					.selectByPrimaryKey(userComplaintsKey.getComplaintId());
			ComplaintsDetails complaintDetail = populateComplaintDetailsFromComplaint(complaint);
			fullComplaintDetail.setComplaintsDetails(complaintDetail);
			allComplaintDetails.add(fullComplaintDetail);
		}

		return allComplaintDetails;
	}

	private ComplaintsDetails populateComplaintDetailsFromComplaint(
			Complaints complaint) {

		ComplaintsDetails complaintDetail = new ComplaintsDetails();
		complaintDetail.setComplaintId(complaint.getComplaintId());
		complaintDetail.setComments(complaint.getComments());
		complaintDetail.setComplaintStatus(complaint.getComplaintStatus());
		complaintDetail.setDepartmentId(complaint.getDepartmentId());
		complaintDetail.setCreationTime(complaint.getCreateDate());
		complaintDetail.setLastUpdateTime(complaint.getLastUpdateDate());
		complaintDetail.setDepartmentId(complaint.getDepartmentId());
		return complaintDetail;
	}

	@Override
	public List<Images> getComplaintImages(Integer complaintId) {
		ComplaintImagesExample complaintImagesExample = new ComplaintImagesExample();
		complaintImagesExample.createCriteria().andComplaintIdEqualTo(complaintId);
		
		List<ComplaintImagesKey> complaintImagesKeys = complaintImagesMapper.selectByExample(complaintImagesExample);
		List<Images> complaintImages = new ArrayList<>();
		for (ComplaintImagesKey complaintImagesKey : complaintImagesKeys) {
			Images image = imagesMapper.selectByPrimaryKey(complaintImagesKey.getImageId());
			complaintImages.add(image);
		}
		
		return complaintImages;
	}

	@Override
	public ComplaintsDetails fetchComplaintDetails(Integer complaintId) {
		Complaints complaint = complaintsMapper.selectByPrimaryKey(complaintId);
		return populateComplaintDetailsFromComplaint(complaint);
	}

	@Override
	public int updateComplaint(Integer complaintId, String status) {
		Complaints complaint = new Complaints();
		complaint.setComplaintId(complaintId);
		complaint.setComplaintStatus(status);
		complaint.setLastUpdateDate(new Date());
		return complaintsMapper.updateByPrimaryKeySelective(complaint);
	}

}
