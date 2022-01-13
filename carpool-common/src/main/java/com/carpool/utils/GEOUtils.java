package com.carpool.utils;


import ch.hsr.geohash.GeoHash;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zuimeideshiguang on 18/2/23.
 * 距离工具类
 */
public class GEOUtils {


  /*	geo_code长度和距离的对照表：

   length	width	height
	1	5,009.4km	4,992.6km
	2	1,252.3km	624.1km
	3	156.5km	156km
	4	39.1km	19.5km
	5	4.9km	4.9km
	6	1.2km	609.4m
	7	152.9m	152.4m
	8	38.2m	19m
	9	4.8m	4.8m
	10	1.2m	59.5cm
	11	14.9cm	14.9cm
	12	3.7cm	1.9cm*/

    private static final int RANGE = 4;

    /**
     * 生成查询范围（8个方位）
     * @param custLongitude
     * @param custLatitude
     * @return
     */
    public static List<Object> createGeoCodes(Double custLongitude , Double custLatitude){
        List<Object> geoCodes = new ArrayList<>();
        if(null != custLongitude && null != custLatitude){
            //计算当前8个方位内的geo code 码，查询更加精确
            GeoHash geoHash = GeoHash.withCharacterPrecision(custLatitude, custLongitude, RANGE );
            // 当前
            geoCodes.add(geoHash.toBase32());
            //System.out.println(geoHash.toBase32());
            // N, NE, E, SE, S, SW, W, NW
            GeoHash[] adjacent = geoHash.getAdjacent();
            for (GeoHash hash : adjacent) {
                geoCodes.add(hash.toBase32());
            }
        }else {
            return null;
        }
        return geoCodes;
    }

    /**
     * 生成查询范围（8个方位）
     * @param custLongitude
     * @param custLatitude
     * @return
     */
    public static String[] getGeoCodes(Double custLongitude , Double custLatitude){
        String[] geoCodes = new String[9];
        if(null != custLongitude && null != custLatitude){
            //计算当前8个方位内的geo code 码，查询更加精确
            GeoHash geoHash = GeoHash.withCharacterPrecision(custLatitude, custLongitude, RANGE);

            GeoHash[] adjacent = geoHash.getAdjacent();
            int i =0 ;
            for (GeoHash hash : adjacent) {
                geoCodes[i] = (hash.toBase32());
               // System.out.println(geoCodes[i]);
                i++;
            }
            geoCodes[8]=geoHash.toBase32();
            //System.out.println(geoCodes[8]);
        }else {
            return null;
        }
        return geoCodes;
    }

    /**
     * 生成当前经纬度geo code
     * @param custLongitude
     * @param custLatitude
     * @return
     */
    public static String cateGeoCode(Double custLongitude , Double custLatitude){
        if(null == custLongitude || null == custLatitude)return  null;

        return   GeohashUtils.encodeLatLon(custLatitude,custLongitude,RANGE);
    }

    /**
     * 计算距离
     * @param custLongitude
     * @param custLatitude
     * @param longitude
     * @param latitude
     * @return
     */
    public static Double calcDistance(Double custLongitude , Double custLatitude ,Double longitude , Double latitude ){
        if (custLatitude == null || custLongitude == null) return 0.00;
        SpatialContext spatialContext = SpatialContext.GEO;
        return spatialContext.calcDistance(spatialContext.makePoint(custLongitude, custLatitude),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }

    public static void main(String[] args) {
        getGeoCodes(Double.valueOf(116.362090),Double.valueOf(40.076060));
        System.out.println(cateGeoCode(Double.valueOf(116.362090),Double.valueOf(40.076060)));
        System.out.println(calcDistance(Double.valueOf(116.362090),Double.valueOf(40.086060),Double.valueOf(116.362090),Double.valueOf(40.076060)));
    }
}
