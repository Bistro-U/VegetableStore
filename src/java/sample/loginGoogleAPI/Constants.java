/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.loginGoogleAPI;

/**
 *
 * @author Admin
 */
public class Constants {

    public static String GOOGLE_CLIENT_ID = "635566642213-da1mihklj4lmogoqc55bvm5ap0vm6gdf.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "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";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/VegetableStore/LoginGGController";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
