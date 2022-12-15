package com.sebone.main.data;

public class AddressDo {
   /*
    * @className- AddressDo
    * @objective- creating getter setter for the AddressDo entities
    */
   private String addressLine;
   private String landmark;
   private String city;
   private State state;

   //getter setter for the variables
   public String getAddressLine() {
      return addressLine;
   }

   public void setAddressLine(String addressLine) {
      this.addressLine = addressLine;
   }

   public String getLandmark() {
      return landmark;
   }

   public void setLandmark(String landmark) {
      this.landmark = landmark;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public State getState() {
      return state;
   }

   public void setState(State state) {
      this.state = state;
   }

   //constructor
   public AddressDo(String addressLine, String landmark, String city, State state) {
      this.addressLine = addressLine;
      this.landmark = landmark;
      this.city = city;
      this.state = state;
   }
}
