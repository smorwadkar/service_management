package com.service.manager.user.persistence;

public class UserAddressesKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_addresses.user_id
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_addresses.address_id
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    private Integer addressId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_addresses.user_id
     *
     * @return the value of user_addresses.user_id
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_addresses.user_id
     *
     * @param userId the value for user_addresses.user_id
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_addresses.address_id
     *
     * @return the value of user_addresses.address_id
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_addresses.address_id
     *
     * @param addressId the value for user_addresses.address_id
     *
     * @mbggenerated Sun Feb 12 00:05:38 IST 2017
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}