#!/usr/bin/env python

num_to_word = {
    1: len("one"),
    2: len("two"),
    3: len("three"),
    4: len("four"),
    5: len("five"),
    6: len("six"),
    7: len("seven"),
    8: len("eight"),
    9: len("nine"),
    10: len("ten"),
    11: len("eleven"),
    12: len("twelve"),
    13: len("thirteen"),
    14: len("fourteen"),
    15: len("fifteen"),
    16: len("sixteen"),
    17: len("seventeen"),
    18: len("eighteen"),
    19: len("nineteen"),
    20: len("twenty"),
    30: len("thirty"),
    40: len("forty"),
    50: len("fifty"),
    60: len("sixty"),
    70: len("seventy"),
    80: len("eighty"),
    90: len("ninety"),
}

def getNumCount(num):
    if num <= 20:
        return num_to_word[num]
    else:
        count = 0
        onesDigit = num % 10
        tensDigit = (num / 10) * 10
        count += num_to_word[tensDigit]
        if onesDigit is not 0:
            count += num_to_word[onesDigit]
    return count

def numLetterCount():
    count = 0
    for n in range(1, 1000):
        if n < 100:
            count += getNumCount(n)
        else:
            count += num_to_word[n/100]
            count += len("hundred")
            if n % 100 != 0:
                count += len("and")
                n %= 100
                count += getNumCount(n)
    return count + 11




print numLetterCount()
