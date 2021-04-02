const vmap = '0123456789ABCDEFGHJKLMNPRSTUVWXYZ'
const base = '34'
const vin = 'ZZZZZZZZZZZZZZZZZ'
var wmi = vmapEncode(vin.substr(0, 3), base)
var vds = vmapEncode(vin.substr(3, 8), base)
var ser = vmapEncode(vin.substr(8), base)

console.log(vmapDecode(vmapEncode(vin.substr(0, 3), base), base, 3))


console.log(wmi)
console.log(vds)
console.log(ser)


function vmapIndexOf(c) {
    return vmap.indexOf(c.toUpperCase())
}

function vmapEncode(data, base) {
    var vrad = 0
    var exp = 0
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
        if (0 < vmapi < 34) {
            //console.log(vmapi)
            //console.log(vmap.substring(vmapi - 1, vmapi))
            out += vmap.substring(vmapi - 1, vmapi)
        }
    }
    return out
}

console.log('vin: ' + vin)
console.log('encoding section')
    //vmapDecode(22900, 34, 3)
wmiEn = vmapEncode(vin.substring(0, 3), base)
vdsEn = vmapEncode(vin.substring(3, 8), base)
serEn = vmapEncode(vin.substring(8), base)
console.log('deCoding section below ')
var decode = ''
var swmi = vmapDecode(wmiEn, base, wmiEn.toString().length)
var svds = vmapDecode(vdsEn, base, vdsEn.toString().length)
var sser = vmapDecode(serEn, base, serEn.toString().length)
console.log(swmi)
console.log(svds)
console.log(sser)
decode = swmi.toString() + svds.toString() + sser.toString()
console.log()
if (vin === decode) {
    console.log(decode.toString() + " (the decode) \n" + vin + " (the vin) ")
}