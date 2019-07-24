package com.example.sys;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddUserTests {

	@Test
	public void contextLoads() {
		String name="admin";
		String password="123456";
		String salt= UUID.randomUUID().toString();
		System.out.println(salt);
		String newPassword = new SimpleHash("MD5", password,  ByteSource.Util.bytes(salt), 1024).toHex();
		System.out.println(newPassword);

	}

}
