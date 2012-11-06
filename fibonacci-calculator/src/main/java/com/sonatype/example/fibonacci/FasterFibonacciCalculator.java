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

public class FasterFibonacciCalculator
{
    private static final Logger log = LoggerFactory.getLogger(FasterFibonacciCalculator.class);

    // position in the sequence
    public long valueAtPosition(int position) {
        checkArgument(position > 0, "Position must be a positive value");

        if (position == 1) {
            return 1;
        }

        long lastValue = 0;
        long previousOne = 1;
        long previousTwo = 1;

        for (int currentPosition = 2; currentPosition < position; currentPosition++) {
            lastValue = previousOne + previousTwo;
            previousTwo = previousOne;
            previousOne = lastValue;
            log.info("{}: {}", currentPosition, lastValue);
        }

        return lastValue;
    }
}
