package com.cbo.riskmanagement.model.un;


import jakarta.persistence.*;

@Entity
@Table(name = "UNsanctions")
public class UNSanctions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "DataId")
    private String DataId;


    @Column(name = "FirstName", columnDefinition = "text")
    private String FirstName;

    @Column(name = "SecondName", columnDefinition = "text")
    private String SecondName;

    @Column(name = "FullName", columnDefinition = "text")
    private String fullName;


    @Column(name = "Type", columnDefinition = "text")
    private String Type;

    @Column(name = "ReferenceNumber", columnDefinition = "text")
    private String ReferenceNumber;

    @Column(name = "ListedOn", columnDefinition = "text")
    private String ListedOn;

    @Column(name = "Comment", columnDefinition = "text")
    private String Comment;

    @Column(name = "Designation", columnDefinition = "text")
    private String Designation;

    @Column(name = "Nationality", columnDefinition = "text")
    private String Nationality;

    @Column(name = "ListType", columnDefinition = "text")
    private String ListType;

    @Column(name = "LastDayUpdated", columnDefinition = "text")
    private String LastDayUpdated;

    @Column(name = "Alias", columnDefinition = "text")
    private String Alias;

    @Column(name = "IndividualAddress", columnDefinition = "text")
    private String IndividualAddress;

    @Column(name = "DateOfBirth", columnDefinition = "text")
    private String DateOfBirth;

    @Column(name = "PlaceOfBirth", columnDefinition = "text")
    private String PlaceOfBirth;

    @Column(name = "IndividualDocument", columnDefinition = "text")
    private String IndividualDocument;

    public UNSanctions() {

    }

    public UNSanctions(long id, String dataId, String firstName, String secondName, String FullName, String type, String referenceNumber,
                       String listedOn, String comment, String designation, String nationality, String listType, String lastDayUpdated, String alias,
                       String individualAddress) {
        Id = id;
        DataId = dataId;
        FirstName = firstName;
        SecondName = secondName;
        fullName = FullName;
        Type = type;
        ReferenceNumber = referenceNumber;
        ListedOn = listedOn;
        Comment = comment;
        Designation = designation;
        Nationality = nationality;
        ListType = listType;
        LastDayUpdated = lastDayUpdated;
        Alias = alias;
        IndividualAddress = individualAddress;
    }



    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDataId() {
        return DataId;
    }

    public void setDataId(String dataId) {
        DataId = dataId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getFullName() {
        return fullName;
    }


    public void setFullName(String FullName) {
        fullName = FullName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getReferenceNumber() {
        return ReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
    }

    public String getListedOn() {
        return ListedOn;
    }

    public void setListedOn(String listedOn) {
        ListedOn = listedOn;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getListType() {
        return ListType;
    }

    public void setListType(String listType) {
        ListType = listType;
    }

    public String getLastDayUpdated() {
        return LastDayUpdated;
    }

    public void setLastDayUpdated(String lastDayUpdated) {
        LastDayUpdated = lastDayUpdated;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getIndividualAddress() {
        return IndividualAddress;
    }

    public void setIndividualAddress(String individualAddress) {
        IndividualAddress = individualAddress;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return PlaceOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        PlaceOfBirth = placeOfBirth;
    }

    public String getIndividualDocument() {
        return IndividualDocument;
    }

    public void setIndividualDocument(String individualDocument) {
        IndividualDocument = individualDocument;
    }






}


