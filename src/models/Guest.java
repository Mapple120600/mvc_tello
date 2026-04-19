package models;

public class Guest 
{
    private String name;
    private String cellphone;
    private String gender;
    private String birthDate;
    private String address;
    private boolean acceptedTerms;

    @Override
    public String toString() 
    {
        return name + ";" + cellphone + ";" + gender + ";" + birthDate + ";" 
             + address + ";" + (acceptedTerms ? "1" : "0");
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCellphone() { return cellphone; }
    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isAcceptedTerms() { return acceptedTerms; }
    public void setAcceptedTerms(boolean acceptedTerms) { this.acceptedTerms = acceptedTerms; }
}