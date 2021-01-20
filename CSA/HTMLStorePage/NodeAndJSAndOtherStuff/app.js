//print("hi") is the python version of console printing
//console.log("hi") // is the JS version, semicolons are optional
//System.out.println("hi"); is the java version of console printing

/*
multi
line
comments
*/

var name = "bander"
let number = 0
const pi = 3.1415926
    //console.log(name)
    //console.log("hi " + (number + pi))

var x1 = 0
var y1 = 0
var x2 = 5
var y2 = 5
var d = ((x2 - x1) ** 2 + (y2 - y1) ** 2) ** .5
    //console.log(Math.round(d))

var someonesName = "Andrew Jackson"

console.log(someonesName.substring(0, someonesName.indexOf(" ")))
console.log(someonesName.substring(someonesName.indexOf(" ") + 1))

var inputString = "100|EA|1.29"
var inputString2 = "87|EA|0.59"
var inputString3 = "56|EA|2.15"

console.log(total(inputString))
console.log(total(inputString2))
console.log(total(inputString3))
console.log(total(inputString) + total(inputString2) + total(inputString3))

console.log(charToBin('a'))

function charToBin(charIn) {
    var binOut = ""
    charAsInt = charIn.charCodeAt(0)
    for (i = 7; i >= 0; i--) {
        if (Math.floor(charAsInt / (2 ** i)) == 1) {
            binOut += "1"
            charAsInt -= (2 ** i)
        } else {
            binOut += "0"
        }
    }
    return binOut
}

function total(stringIn) {
    let stringSplit = stringIn.split("|")
    return Number(stringSplit[0]) * Number(stringSplit[2])
}