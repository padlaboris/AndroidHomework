package com.example.padlabear.hw;


import com.example.padlabear.hw.http.HttpClient;
import com.example.padlabear.hw.json.gson.GsonUser;
import com.example.padlabear.hw.json.gson.GsonUserList;
import com.example.padlabear.hw.json.gson.GsonUserListParser;
import com.example.padlabear.hw.json.gson.GsonUserParser;
import com.example.padlabear.hw.json.jsonObject.JsonUser;
import com.example.padlabear.hw.json.jsonObject.JsonUserList;
import com.example.padlabear.hw.json.jsonObject.JsonUserListParser;
import com.example.padlabear.hw.json.jsonObject.JsonUserParser;
import com.example.padlabear.hw.util.DateUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Constants.SDK_VERSION
)
public class UserParseTest {

    private static final String USER_USER_LIST_JSON = "user/userList.json";
    private static final String USER_USER_JSON = "user/user.json";
    private HttpClient httpClient;
    private String sourceArray;
    private String source;

    @Before
    public void setUp() throws Exception {
        httpClient = mock(HttpClient.class);
        sourceArray = Mocks.stream(USER_USER_LIST_JSON);
        source = Mocks.stream(USER_USER_JSON);
    }


    @Test
    public void parseGsonUserTest() throws Exception {
        when(httpClient.request(anyString())).thenReturn(source);
        final String response = httpClient.request("http://api/someResourse");
        final GsonUser parseResult = new GsonUserParser(response).parse();
        assertNotNull(parseResult);
        checkGsonUser(parseResult);
    }


    @Test
    public void parseGsonUsersTest() throws Exception {
        when(httpClient.request(anyString())).thenReturn(sourceArray);
        final String response = httpClient.request("http://api/someResourse");
        final GsonUserList parseResult = new GsonUserListParser(response).parse();
        assertNotNull(parseResult);
        final List<GsonUser> users = parseResult.getUsers();
        assertEquals(3, users.size());
        final GsonUser user = users.iterator().next();
        checkGsonUser(user);
    }

    @Test
    public void parseJsonUserTest() throws Exception {
        when(httpClient.request(anyString())).thenReturn(source);
        final String response = httpClient.request("http://api/someResourse");
        final JsonUser parseResult = new JsonUserParser(response).parse();
        assertNotNull(parseResult);
        checkJsonUser(parseResult);
    }

    @Test
    public void parseJsonUsersTest() throws Exception {
        when(httpClient.request(anyString())).thenReturn(sourceArray);
        final String response = httpClient.request("http://api/someResourse");
        final JsonUserList parseResult = new JsonUserListParser(response).parse();
        assertNotNull(parseResult);
        final List<JsonUser> users = parseResult.getUsers();
        assertEquals(3, users.size());
        final JsonUser user = users.iterator().next();
        checkJsonUser(user);
    }

    private void checkGsonUser(final GsonUser user) throws ParseException {
        assertEquals("Jon", user.getFirstName());
        assertEquals("Snow", user.getLastName());
        assertEquals("25", user.getAge());
        assertEquals("Winterfall", user.getLocation());
        assertEquals(DateUtil.getDate("10/10/1900"), user.getRegistered());
    }


    private void checkJsonUser(final JsonUser user) throws Exception {
        assertEquals("Jon", user.getFirstName());
        assertEquals("Snow", user.getLastName());
        assertEquals("25", user.getAge());
        assertEquals("Winterfall", user.getLocation());
        assertEquals(DateUtil.getDate("10/10/1900"), user.getRegistered());
    }
}