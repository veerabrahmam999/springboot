package com.ticket.demo.model;




import jakarta.persistence.*;
/*Entity: Marks a Java class as a JPA entity, indicating that it represents a table in the database. */

@Entity
/* Table: Specifies the name of the database table to which this entity is mapped.
If you don't specify it, the table name will default to the class name.
 */
@Table(name="Passenger")
public class Passenger {

    /* ID: Designates the primary key field of the entity.*/
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int passengerId;
        private String name;
        private Integer age;
        private String gender;

        public long getPassengerId() {
            return passengerId;
        }

        public void setPassengerId(int passengerId) {
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


