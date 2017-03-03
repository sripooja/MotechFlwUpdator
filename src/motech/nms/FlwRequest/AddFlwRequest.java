package motech.nms.FlwRequest;

/**
 * Created by atish on 1/6/16.
 */
public class AddFlwRequest {

    private String name;
    private String mctsFlwId;
    private Long contactNumber;
    private Integer stateId;
    private Integer districtId;
    private String talukaId;
    private Integer phcId;
    private Integer subcentreId;
    private Integer villageId;
    private Integer healthblockId;
    private String type;
    private String gfStatus;

    public AddFlwRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMctsFlwId() {
        return mctsFlwId;
    }

    public void setMctsFlwId(String mctsFlwId) {
        this.mctsFlwId = mctsFlwId;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getTalukaId() {
        return talukaId;
    }

    public void setTalukaId(String talukaId) {
        this.talukaId = talukaId;
    }

    public Integer getPhcId() {
        return phcId;
    }

    public void setPhcId(Integer phcId) {
        this.phcId = phcId;
    }

    public Integer getSubcentreId() {
        return subcentreId;
    }

    public void setSubcentreId(Integer subcentreId) {
        this.subcentreId = subcentreId;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public Integer getHealthblockId() {
        return healthblockId;
    }

    public void setHealthblockId(Integer healthblockId) {
        this.healthblockId = healthblockId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGfStatus() {
        return gfStatus;
    }

    public void setGfStatus(String gfStatus) {
        this.gfStatus = gfStatus;
    }

    @Override public String toString() {
        return "AddFlwRequest{" +
                "name='" + name + '\'' +
                ", mctsFlwId='" + mctsFlwId + '\'' +
                ", contactNumber=" + contactNumber +
                ", stateId=" + stateId +
                ", districtId=" + districtId +
                ", talukaId='" + talukaId + '\'' +
                ", phcId=" + phcId +
                ", subcentreId=" + subcentreId +
                ", villageId=" + villageId +
                ", healthblockId=" + healthblockId +
                ", type='" + type + '\'' +
                ", gfStatus='" + gfStatus + '\'' +
                '}';
    }
}
