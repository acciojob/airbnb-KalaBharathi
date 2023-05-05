package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HotelManagementRepository {
    HashMap<String,Hotel> hotelMap=new HashMap<>();
    static HashMap<Integer,User> userMap=new HashMap<>();

    HashMap<String,Booking> bookingMap=new HashMap<>();
    public static Integer addUser(User user) {
        userMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public String addHotel(Hotel hotel) {
        if(!Objects.nonNull(hotel) || !(Objects.nonNull(hotel.getHotelName()))){
            return "FAILURE";
        }
        else if(hotelMap.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        else {
            hotelMap.put(hotel.getHotelName(),hotel);
            return "SUCCESS";
        }
    }

    public String getHotelWithMostFacilities() {
        String hotelNamewithMoreFacilities="";
        int max=0;
        for(String hotelName:hotelMap.keySet()){
            Hotel hotel=hotelMap.get(hotelName);
            if(max<hotel.getFacilities().size()){
                max=hotel.getFacilities().size();
                hotelNamewithMoreFacilities=hotel.getHotelName();
            }
            else if(max==hotel.getFacilities().size()){
                if(hotelNamewithMoreFacilities.compareTo(hotelName)>0){
                    hotelNamewithMoreFacilities=hotel.getHotelName();
                }
            }
        }
        return hotelNamewithMoreFacilities;
    }

    public int bookARoom(Booking booking) {
        String bookingId= UUID.randomUUID().toString();
        Hotel hotel=hotelMap.get(booking.getHotelName());
        if(booking.getNoOfRooms()>hotel.getAvailableRooms()){
            return -1;
        }
        bookingMap.put(bookingId,booking);
        int amount=booking.getNoOfRooms()*hotel.getPricePerNight();
        return amount;
    }

    public int getBookings(Integer aadharCard) {
        int count=0;
        for(String bookinId:bookingMap.keySet()){
            Booking booking=bookingMap.get(bookinId);
            if(booking.getBookingAadharCard() == aadharCard){
                count++;
            }
        }
        return count;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel=hotelMap.get(hotelName);
        List<Facility> existingFacilities=hotel.getFacilities();
        for(Facility facility:newFacilities){
            if(!existingFacilities.contains(facility)){
                existingFacilities.add(facility);
            }
        }
        hotel.setFacilities(existingFacilities);
        hotelMap.put(hotelName,hotel);
        return hotel;
    }
}
