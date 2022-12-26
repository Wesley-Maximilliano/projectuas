package com.if3a.mobilelegendsrework.api;

import com.if3a.mobilelegendsrework.model.role.Role;
import com.if3a.mobilelegendsrework.model.role.RootRole;
import com.if3a.mobilelegendsrework.model.specially.RootSpecially;
import com.if3a.mobilelegendsrework.model.specially.Specially;
import com.if3a.mobilelegendsrework.model.specially.SpeciallyHero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIRequestData2 {
    @GET("specially")
    Call<RootSpecially> getSpecially();

    @FormUrlEncoded
    @GET("specially")
    Call<Specially> createspecially(@Field("specially_id") int id,
                                    @Field("specially_name") String name);

    @GET("specially")
    Call<RootSpecially> getSpeciallyHero(@Query("speciallyName") String speciallyName);

    @FormUrlEncoded
    @GET("specially")
    Call<List<SpeciallyHero>> createSpeciallyHero(@Query("speciallyName") String speciallyName,
                                                  @Field("hero_name") String hero_name,
                                                  @Field("hero_avatar") String hero_avatar,
                                                  @Field("hero_role") String herro_role,
                                                  @Field("hero_specially") String hero_specially);

    @GET("role")
    Call<RootRole> getRole();
}
