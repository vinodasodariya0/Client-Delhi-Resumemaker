package nithra.resume.maker.cv.builder.app.Models;

public class DB_Items {
    int academic_id;
    String course;
    String designation;
    String discription;
    String duration;
    String email;
    String end_date;
    String extra_id;
    String institute;
    String join_date;
    String name;
    String organization;
    String percentage;
    String phone;
    String photo;
    String pro_title;
    int profile_id;
    int project_id;
    int reference_id;
    String role;
    String t_size;
    String title;
    String type;
    String value;
    int workid;
    String yop;

    public String getExtra_id() {
        return this.extra_id;
    }

    public void setExtra_id(String str) {
        this.extra_id = str;
    }

    public DB_Items(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21) {
        this.profile_id = i;
        this.academic_id = i2;
        this.reference_id = i3;
        this.project_id = i4;
        this.workid = i5;
        this.course = str;
        this.name = str2;
        this.title = str3;
        this.value = str4;
        this.organization = str5;
        this.photo = str6;
        this.institute = str7;
        this.percentage = str8;
        this.yop = str9;
        this.designation = str10;
        this.type = str11;
        this.join_date = str12;
        this.end_date = str13;
        this.role = str14;
        this.pro_title = str15;
        this.discription = str16;
        this.duration = str17;
        this.t_size = str18;
        this.phone = str19;
        this.email = str20;
        this.extra_id = str21;
    }

    public DB_Items(String str) {
        this.photo = str;
    }

    public DB_Items(String str, String str2) {
        this.title = str;
        this.value = str2;
    }

    public DB_Items(int i) {
        this.academic_id = i;
    }

    public DB_Items(int i, String str) {
        this.profile_id = i;
        this.course = str;
    }

    public DB_Items() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getT_size() {
        return this.t_size;
    }

    public void setT_size(String str) {
        this.t_size = str;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String str) {
        this.discription = str;
    }

    public String getPro_title() {
        return this.pro_title;
    }

    public void setPro_title(String str) {
        this.pro_title = str;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public String getJoin_date() {
        return this.join_date;
    }

    public void setJoin_date(String str) {
        this.join_date = str;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String str) {
        this.end_date = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String str) {
        this.designation = str;
    }

    public String getYop() {
        return this.yop;
    }

    public void setYop(String str) {
        this.yop = str;
    }

    public String getPercentage() {
        return this.percentage;
    }

    public void setPercentage(String str) {
        this.percentage = str;
    }

    public String getInstitute() {
        return this.institute;
    }

    public void setInstitute(String str) {
        this.institute = str;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public int getProject_id() {
        return this.project_id;
    }

    public void setProject_id(int i) {
        this.project_id = i;
    }

    public int getReference_id() {
        return this.reference_id;
    }

    public void setReference_id(int i) {
        this.reference_id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getWorkid() {
        return this.workid;
    }

    public void setWorkid(int i) {
        this.workid = i;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String str) {
        this.organization = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public int getAcademic_id() {
        return this.academic_id;
    }

    public void setAcademic_id(int i) {
        this.academic_id = i;
    }

    public int getProfile_id() {
        return this.profile_id;
    }

    public void setProfile_id(int i) {
        this.profile_id = i;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String str) {
        this.course = str;
    }
}
