package ru.erasko.task1;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static class Builder {
        public String firstName;
        public String lastName;
        public String middleName;
        public String country;
        public String address;
        public String phone;
        private  int age;
        private String gender;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }
        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }
        public Person build() {
            return new Person(this);
        }
    }

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        middleName = builder.middleName;
        country = builder.country;
        address = builder.address;
        phone = builder.phone;
        age = builder.age;
        gender = builder.gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}


