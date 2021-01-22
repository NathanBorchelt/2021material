/*
Explain the pros and cons of converting your data into smaller data size when saving it to the database. (at least 3 each){
    Pros{
        send less data and save money spent on servers
        encrypts and protects data, even though it can be easily decoded
        less to try to look through when checking for errors
    }
    Cons{
        Possible data miscalculations when hitting unexpected characters
        takes time to develop the encoding of the data
        takes time to process when wanting to view the data in human terms
    }
}
What size of data will we need if we limit the id to 8 characters?{
    A long long is needed, because ZZZZZZZZ wil produce a 42 bit number, which long can only hold 32-bits, so you must up it to long long to hold up to 64-bits
}
*/

const vmap = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
const base = '37'
const specChar =  '.-_#+?!'

main()

function vmapIndexOf(c) {
    return vmap.indexOf(c.toUpperCase())
}

function vmapEncode(data, base) {
    var vrad = 0
    var exp = 0
    var broken = false
    for(var testChar = 0; testChar<data.length; testChar++){
        if(!vmap.includes(data.charAt(testChar))){
            broken = true
            break
        }
    }
    if(!broken){
            //for each letter
        for (var index = 0; index < data.length; index++) {
            //mval is the index of the letter
            var mVal = vmapIndexOf(data[index]) + 1
                //exp shares the sie of the number
            var exp = (data.length - 1) - index
                //vrad is the cummulative number of the indexOfLetter *(base^exp)
            vrad += (Number(mVal) * Math.pow(base, exp))
                //console.log(data[index], mVal, exp, vrad)
        }
        //console.log(vrad)
    }
    return vrad
}

function vmapDecode(vrad, base, length) {
    //out is decoded chars
    var out = ''
        //exp is the exponent will change with every iteration
    exp = 0
    for (var i = 0; i < length; i++) {
        var vmapi = 0
        var vDelta = 0
        exp = (length - 1) - i

        var vradp = base ** exp
        var vmod = vrad % vradp
        vDelta = vrad - vmod
            //console.log(vDelta, "|", vrad, "|", vmod)
        if (vmod > 0) {
            vDelta = vrad - vmod
            vmapi = vDelta / vradp
            vrad = vmod
                //console.log(vDelta, "|", vrad, "|", vmod)
        } else {
            vDelta = vrad
            vmapi = vrad
                //console.log(vDelta, vmapi)
        }
        if (0 < vmapi < 37) {
            //console.log(vmapi)
            //console.log(vmap.substring(vmapi - 1, vmapi))
            out += vmap.substring(vmapi - 1, vmapi)
        }
    }
    return out
}

function whichSpecChar(strIn){ //finds the index of the special char
    for(var indexOfSpecChar = 0; indexOfSpecChar<specChar.length; indexOfSpecChar++){
        //console.log(specChar.charAt(indexOfSpecChar))
        if(strIn.includes(specChar.charAt(indexOfSpecChar))){
            return indexOfSpecChar
        }
    }
    return 0
}

function locOfSpecChar(strIn,CharIn){
    var valid = true
    for(var specCharLocIndex = 0; specCharLocIndex<specChar.length;specCharLocIndex++){
        if(strIn.charAt(0) === specChar[specCharLocIndex]){
            console.log("There is a specialCharater the start of the ID")
            valid = false
            break
        }
    }
    if(valid){
        var indexSpecFinalLoc = strIn.indexOf(CharIn)
        if(indexSpecFinalLoc == -1){
            return 0
        }
        else{
            return indexSpecFinalLoc
        }
    }
    else{
        return 0
    }
}

function binConversion(number, len = 4){
    var binBasic = number.toString(2)
    //console.log(binBasic)
    while (binBasic.length < len){
        binBasic = "0" + binBasic
    }
    return binBasic
}

function decConversion(binStr){
    return parseInt(binStr,2)
}

function main(){
    var infoIn = "!A12356"
    var specCharIndex = whichSpecChar(infoIn)
    var indexOfSpecCharInString = locOfSpecChar(infoIn,specChar.charAt(specCharIndex))
    var binFirstHalf = binConversion(indexOfSpecCharInString)
    var binSecHalf = binConversion(specCharIndex)
    var binCombined = binFirstHalf+binSecHalf
    //console.log(binFirstHalf)
    //console.log(binSecHalf)
    //console.log(binCombined)
    //console.log(decConversion(binCombined))
    //console.log(binConversion(decConversion(binCombined),8))
    //console.log(binConversion(decConversion(binCombined),8) === binCombined)
    //console.log(infoIn)
    var cleanInfo = infoIn
    for(var cleanIndex = 0; cleanIndex < specChar.length;cleanIndex++){
        cleanInfo = cleanInfo.replace(specChar[cleanIndex],'')
    }
    
    console.log(infoIn)
    console.log(binCombined)
    console.log(vmapEncode(cleanInfo,base))
    console.log(decConversion(binCombined))
}