package com.ibm.cic.br.seeds.javaspringbootmongodb.dao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "customers")
public class Customer {

    @Id
    public String id;

    @Field("emailaddress")
    public String emailAddress;

    @Field("lastname")
    public String lastName;

    @Field("firstname")
    public String firstName;

    @Field("birthdate")
    //@JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    //@JsonSerialize(using = JsonDateSerializer.class)
    public Date birthDate;
}


