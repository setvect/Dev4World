package com.dev4world.z_annotation;

import static org.hamcrest.CoreMatchers.is;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.dev4world.z_etc.MyComment;
import com.dev4world.z_etc.SampleObject;

public class AnnotationTestCase {

	@Test
	public void annotation테스트() throws ClassNotFoundException, NoSuchMethodException, SecurityException {

		@SuppressWarnings("unchecked")
		Class<SampleObject> sampleClass = (Class<SampleObject>) Class
				.forName("com.dev4world.z_annotation.SampleObject");

		Method method = sampleClass.getMethod("getMessage", null);

		MyComment myComment = method.getAnnotation(MyComment.class);
		Assert.assertNotNull(myComment);
		System.out.println(myComment);

		Assert.assertThat(myComment.value(), is("해담이가 아니고 해다미"));
	}
}
