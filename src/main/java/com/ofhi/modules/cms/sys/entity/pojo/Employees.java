package com.ofhi.modules.cms.sys.entity.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "employees")
public class Employees {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "birth_date")
    private java.util.Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Object gender;

    @Column(name = "hire_date")
    private java.util.Date hireDate;

}
