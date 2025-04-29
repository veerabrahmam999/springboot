package com.ticket.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="Passenger")
public class Passenger {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long passengerId;
        private String name;
        private Integer age;
        private String gender;

        public Long getPassengerId() {
            return passengerId;
        }

        public void setPassengerId(Long passengerId) {
            this.passengerId = passengerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Passenger{" +
                    "passengerId=" + passengerId +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }


