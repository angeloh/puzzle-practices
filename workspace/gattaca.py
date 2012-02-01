#!/usr/bin/env python
# gattaca.py - A solution to Facebook's 'Gattaca' problem.
#
# Copyright (c) 2009, Alan Saqui <alan.saqui@gmail.com>
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#     * Redistributions of source code must retain the above copyright
#       notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above copyright
#       notice, this list of conditions and the following disclaimer in the
#       documentation and/or other materials provided with the distribution.
#     * Neither the name of the <organization> nor the
#       names of its contributors may be used to endorse or promote products
#       derived from this software without specific prior written permission.
# 
# THIS SOFTWARE IS PROVIDED BY <copyright holder> ''AS IS'' AND ANY
# EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL <copyright holder> BE LIABLE FOR ANY
# DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
import sys
import re

# Small container class for intervals.
class Interval:
    def __init__(self, start, end, val):
        self.start = start
        self.end = end
        self.val = val

# Get the list of the intervals from the input file.
def get_intervals():
    intervals = []
    f = open(sys.argv[1], 'r')
    for line in f:
        fields = line.strip().split()
        if len(fields) == 3:
            # Interval has 3 fields (start, end, val)
            intervals.append(Interval(*[int(x) for x in fields]))
    return intervals

# Returns True if intervals a and b do not overlap, false otherwise.
def comp_intv(a, b):
    if a.end < b.start:
        return True
    elif b.end < a.start:
        return True
    else:
        return False

# Find the max total from a given starting interval and list of compatible
# intervals.
def find_max(start, allowed):
    # Find all compatible intervals.
    my_allowed = [x for x in allowed if comp_intv(x, start)]
    if not my_allowed:
        # No more possible additions, so max is just this interval's val
        return start.val
    else:
        # Otherwise, max is the addition of start's val plus the max val
        # out of start's possible additions.
        return start.val + max([find_max(x, my_allowed) for x in my_allowed])

intervals = get_intervals()
total = max([find_max(intv, intervals) for intv in intervals])
print total

