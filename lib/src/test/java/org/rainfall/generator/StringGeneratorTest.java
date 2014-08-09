package org.rainfall.generator;

import org.junit.Test;
import org.rainfall.ObjectGenerator;

import org.junit.Assert;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Aurelien Broszniowski
 */

public class StringGeneratorTest {

  @Test
  public void testGenerateInvalidFixedLengthString() {
    try {
      ObjectGenerator generator = StringGenerator.fixedLength(-1);
      generator.generate(0);
      Assert.fail("Should not be able to generate a negative length String");
    } catch (Exception e) {
      // Expected
    }
  }

  @Test
  public void testGenerateZeroLengthString() {
    try {
      ObjectGenerator generator = StringGenerator.fixedLength(0);
      generator.generate(0);
      Assert.fail("Should not be able to generate a 0 length String");
    } catch (Exception e) {
      // Expected
    }
  }

  @Test
  public void testGenerate1CharacterString() {
    int length = 1;
    ObjectGenerator generator = StringGenerator.fixedLength(length);
    String generated = (String)generator.generate(0);
    assertThat(generated.length(), is(equalTo(length)));
  }

  @Test
  public void testGenerate10CharactersString() {
    int length = 10;
    ObjectGenerator generator = StringGenerator.fixedLength(length);
    String generated = (String)generator.generate(0);
    assertThat(generated.length(), is(equalTo(length)));
  }

  @Test
  public void testGenerate100CharactersString() {
    int length = 100;
    ObjectGenerator generator = StringGenerator.fixedLength(length);
    String generated = (String)generator.generate(0);
    assertThat(generated.length(), is(equalTo(length)));
  }

 @Test
  public void testTwoGenerationsGiveDifferentStringInstances() {
    int length = 10;
    ObjectGenerator generator = StringGenerator.fixedLength(length);
    String generated1 = (String)generator.generate(0);
    String generated2 = (String)generator.generate(0);
   assertThat(generated1, not(sameInstance(generated2)));
  }


}
