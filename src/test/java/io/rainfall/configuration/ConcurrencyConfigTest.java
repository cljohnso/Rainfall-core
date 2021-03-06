/*
 * Copyright 2014 Aurélien Broszniowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rainfall.configuration;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Aurelien Broszniowski
 */

public class ConcurrencyConfigTest {

  @Test
  public void nbIterationsLowerThanNbThreadsTest() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 3L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(1, 3L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(2, 3L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(3, 3L), is(equalTo(0L)));
  }

  @Test
  public void nbIterationsEqNbThreadsTest() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 4L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(1, 4L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(2, 4L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(3, 4L), is(equalTo(1L)));
  }

  @Test
  public void nbIterationsHigherThanNbThreadsTest() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 5L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(1, 5L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(2, 5L), is(equalTo(1L)));
    assertThat(config.getNbIterationsForThread(3, 5L), is(equalTo(1L)));
  }

  @Test
  public void nbIterationsAlmostDoubleThanNbThreadsTest() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 7L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(1, 7L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(2, 7L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(3, 7L), is(equalTo(1L)));
  }

  @Test
  public void nbIterationsDoubleThanNbThreadsTest() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 8L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(1, 8L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(2, 8L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(3, 8L), is(equalTo(2L)));
  }

  @Test
  public void nbIterationsAlmostTripleThanNbThreadsTest() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 10L), is(equalTo(3L)));
    assertThat(config.getNbIterationsForThread(1, 10L), is(equalTo(3L)));
    assertThat(config.getNbIterationsForThread(2, 10L), is(equalTo(2L)));
    assertThat(config.getNbIterationsForThread(3, 10L), is(equalTo(2L)));
  }

  @Test
  public void nbIterationsTooHighForInteger() {
    ConcurrencyConfig config = new ConcurrencyConfig().threads(4);
    assertThat(config.getNbIterationsForThread(0, 30000000000L), is(equalTo(7500000000L)));
    assertThat(config.getNbIterationsForThread(1, 30000000000L), is(equalTo(7500000000L)));
    assertThat(config.getNbIterationsForThread(2, 30000000000L), is(equalTo(7500000000L)));
    assertThat(config.getNbIterationsForThread(3, 30000000000L), is(equalTo(7500000000L)));
  }

}
