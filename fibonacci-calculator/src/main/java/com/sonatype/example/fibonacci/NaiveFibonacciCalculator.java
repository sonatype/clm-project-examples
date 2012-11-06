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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A very inefficient calculator.
 * 
 * http://en.wikipedia.org/wiki/Fibonacci_number
 * http://mathworld.wolfram.com/FibonacciNumber.html
 */
public class NaiveFibonacciCalculator
{
    private static final Logger log = LoggerFactory.getLogger(NaiveFibonacciCalculator.class);

    // position in the sequence
    public long valueAtPosition(int position) {
        checkArgument(position > 0, "Position must be a positive value");

        long lastValue = 0;
        
        for (int currentPosition = 1; currentPosition <= position; currentPosition++) {
            lastValue = calculate(currentPosition);
            log.info("{}: {}", currentPosition, lastValue);
        }
        
        return lastValue;
    }

    private long calculate(int position) {
        if (position <= 1) {
            return position;
        }
        else {
            return calculate(position - 1) + calculate(position - 2);
        }
    }
}
