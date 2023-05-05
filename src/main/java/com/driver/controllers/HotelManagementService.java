package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.List;

public class HotelManagementService {
    HotelManagementRepository hotelManagementRepository=new HotelManagementRepository();

    public String addHotel(Hotel hotel) {
        String added=hotelManagementRepository.addHotel(hotel);
        return added;
    }

    public Integer addUser(User user) {
        Integer aadharNum=HotelManagementRepository.addUser(user);
        return aadharNum;
    }

    public String getHotelWithMostFacilities() {
        String hotelName=hotelManagementRepository.getHotelWithMostFacilities();
        return hotelName;
    }

    public int bookARoom(Booking booking) {
        int amount=hotelManagementRepository.bookARoom(booking);
        return amount;
    }

    public int getBookings(Integer aadharCard) {
        int bookingsDone=hotelManagementRepository.getBookings(aadharCard);
        return bookingsDone;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel=hotelManagementRepository.updateFacilities(newFacilities,hotelName);
        return hotel;
    }
}
