/*
 * Copyright (c) 2007-2012 Sonatype, Inc. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

package com.sonatype.example.fibonacci;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Assumes that if the tested positions are correct then all positions are correct.
 */
public class NaiveFibonacciCalculatorTest
{
    @Test(expected=IllegalArgumentException.class)
    public final void verifyNegativeValueIsNotAllowed() {
        new NaiveFibonacciCalculator().valueAtPosition(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public final void verifyPosition0IsNotAllowed() {
        new NaiveFibonacciCalculator().valueAtPosition(0);
    }    

    @Test
    public final void verifyValueAtPosition1() {
        long value = new NaiveFibonacciCalculator().valueAtPosition(1);
        assertThat(value, is(1l));
    }

    @Test
    public final void verifyValueAtPosition5() {
        long value = new NaiveFibonacciCalculator().valueAtPosition(5);
        assertThat(value, is(5l));
    }
    
    @Test
    public final void verifyValueAtPosition10() {
        long value = new NaiveFibonacciCalculator().valueAtPosition(10);
        assertThat(value, is(55l));
    }
}
