package com.cotivity.online_ticket_reservation_system.utils;

 public class RequiredConstant {

     public static class URLConstant {
        public final static String API_VERSION = "/api/v1";
    }

     public static class FeatureAPIConstant {
         public final static String PAYMENT_API = "/payment";
         public final static String RESERVATION_API = "/reservation";
         public final static String LOCATION_API = "/location";
     }

     public static class MessageConstant {
         public final static String RESERVATION_CREATED = "Reservation added successfully!!!";
         public final static String LOCATION_CREATED = "Location added successfully!!!";
         public final static String PAYMENT_RECEIVED = "Payment successfully!!!";
         public final static String LOCATION_NOT_FOUND = "Location not found!!!";
         public final static String PAYMENT_NOT_FOUND = "Payment not found!!!";
         public final static String UNAUTHORIZED_USER = "UnAuthorized User!!!";
     }

     public static class PathVariableConstant {
         public final static String USER_ID = "/{userId}";
         public final static String ID = "/{id}";
         public final static String LOCATION = "/{locationId}";
     }
}
