package vfh.httpInterface.model;

public class BuildingsDetailWithBLOBs extends BuildingsDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_buildings_detail.floor_detail
     *
     * @mbggenerated Wed Jan 06 17:08:26 CST 2016
     */
    private String floorDetail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_buildings_detail.building_detail
     *
     * @mbggenerated Wed Jan 06 17:08:26 CST 2016
     */
    private String buildingDetail;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_buildings_detail.floor_detail
     *
     * @return the value of tb_buildings_detail.floor_detail
     *
     * @mbggenerated Wed Jan 06 17:08:26 CST 2016
     */
    public String getFloorDetail() {
        return floorDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_buildings_detail.floor_detail
     *
     * @param floorDetail the value for tb_buildings_detail.floor_detail
     *
     * @mbggenerated Wed Jan 06 17:08:26 CST 2016
     */
    public void setFloorDetail(String floorDetail) {
        this.floorDetail = floorDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_buildings_detail.building_detail
     *
     * @return the value of tb_buildings_detail.building_detail
     *
     * @mbggenerated Wed Jan 06 17:08:26 CST 2016
     */
    public String getBuildingDetail() {
        return buildingDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_buildings_detail.building_detail
     *
     * @param buildingDetail the value for tb_buildings_detail.building_detail
     *
     * @mbggenerated Wed Jan 06 17:08:26 CST 2016
     */
    public void setBuildingDetail(String buildingDetail) {
        this.buildingDetail = buildingDetail;
    }
}