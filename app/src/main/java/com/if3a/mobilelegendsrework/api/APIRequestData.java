package com.if3a.mobilelegendsrework.api;

import com.if3a.mobilelegendsrework.model.detail.Skill;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.hero.Root;
import com.if3a.mobilelegendsrework.model.detail.Data;
import com.if3a.mobilelegendsrework.model.detail.RootDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequestData {
    @GET("list")
    Call<Root> getHero();

    @FormUrlEncoded
    @GET("list")
    Call<Hero> createUser(@Field("name") String name,
                          @Field("key") String key,
                          @Field("heroid") String heroid);

    @GET("detail")
    Call<RootDetail> getHeroDetail(@Query("id") String id);


    /*@FormUrlEncoded
    @GET("detail")
    Call<Data> createData(@Query("id") String id,
                          @Field("type") String type,
                          @Field("mag") String mag,
                          @Field("phy") String phy,
                          @Field("alive") String alive,
                          @Field("diff") String diff);*/
    /*@GET("detail")
    Call<Skill> createSkill(@Query("id") int id,
                            @Field("name") String name,
                            @Field("icon") String icon,
                            @Field("des") String des,
                            @Field("tips") String tips);*/
}
