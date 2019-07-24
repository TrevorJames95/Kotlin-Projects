package converter
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var count = 0
    var str: String
    var initialMeasure: Double
    var m1: String
    var m2: String
    var firstMeasure: Double = 0.0
    var finalMeasure: Double = 0.0
    var firstUnit:String = ""
    var secondUnit:String = ""

    var isDistance:Boolean
    var isWeight:Boolean
    var isTemp:Boolean


    do{
        print("Enter what you want to convert (or exit): ")
        var str = scanner.nextLine().toLowerCase()
        val inputArr= str.split(" ").toTypedArray()
        if(inputArr[0] == "exit") break

        else{
            initialMeasure = inputArr[0].toDouble()

            m1 = inputArr[1]
            m2 = inputArr[3]

            if(inputArr.size > 4) {
                if(inputArr[1]== "degrees" || inputArr[1] == "degree"){
                    m1 = inputArr[1] + " " + inputArr[2]
                    m2 = inputArr[4]
                }
                else if(inputArr[3]== "degrees" || inputArr[3] == "degree"){
                    m1 = inputArr[1]
                    m2 = inputArr[3] + " " + inputArr[4]
                }

                if(inputArr[4] == "degrees" || inputArr[4] == "degree"){
                    m1 = inputArr[1] + " " + inputArr[2]
                    m2 = inputArr[4] + " " + inputArr[5]
                }
            }

            isTemp = tempChecker(m1)
            isDistance = distanceChecker(m1)
            isWeight = weightChecker(m1)


            // Error checking for negative weights or distances.
            if(initialMeasure < 0.0 && isDistance){
                println("Length shouldn't be negative.")
                continue
            }
            else if(initialMeasure < 0.0 && isWeight){
                println("Weight shouldn't be negative.")
                continue
            }

            if(isWeight && !weightChecker(m2)){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }
            else if(!isWeight && weightChecker(m2)){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }
            else if(isDistance && !distanceChecker((m2))){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }
            else if(!isDistance && distanceChecker((m2))){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }
            else if(!isTemp && tempChecker((m2))){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }
            else if(isTemp && !tempChecker((m2))){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }
            else if((!isTemp && !tempChecker(m2)) && (!isDistance && !distanceChecker(m2)) && (!isWeight && !weightChecker(m2))){
                println("Conversion from ${unitFinder(m1,initialMeasure)} to ${unitFinder(m2,initialMeasure)} is impossible.")
                continue
            }

            if(isWeight) {
                when (m1) {
                    "g", "gram", "grams" -> {
                        if(initialMeasure == 1.0) firstUnit = "gram"
                        else firstUnit = "grams"
                        firstMeasure = initialMeasure
                    }
                    "kg", "kilogram", "kilograms" ->{
                        if(initialMeasure == 1.0) firstUnit = "kilogram"
                        else firstUnit = "kilograms"
                        firstMeasure = kilCon(initialMeasure)
                    }
                    "mg", "milligram", "milligrams" ->{
                        if(initialMeasure == 1.0) firstUnit = "milligram"
                        else firstUnit = "milligrams"
                        firstMeasure = milliCon(initialMeasure)
                    }
                    "lb", "pound", "pounds" ->{
                        if(initialMeasure == 1.0) firstUnit = "pound"
                        else firstUnit = "pounds"
                        firstMeasure = poundCon(initialMeasure)
                    }
                    "oz", "ounce", "ounces" ->{
                        if(initialMeasure == 1.0) firstUnit = "ounce"
                        else firstUnit = "ounces"
                        firstMeasure = ounceCon(initialMeasure)
                    }
                }

                when (m2) {
                    "g", "gram", "grams" ->{
                        secondUnit = "grams"
                        finalMeasure = firstMeasure
                        if(finalMeasure == 1.0) secondUnit ="gram"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "kg", "kilogram", "kilograms" ->{
                        secondUnit = "kilograms"
                        finalMeasure = gramToKilo(firstMeasure)
                        if(finalMeasure == 1.0) secondUnit ="kilogram"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "mg", "milligram", "milligrams" ->{
                        secondUnit = "milligrams"
                        finalMeasure = gramToMilli(firstMeasure)
                        if(finalMeasure == 1.0) secondUnit ="milligram"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "lb", "pound", "pounds" ->{
                        secondUnit = "pounds"
                        finalMeasure = gramToPound(firstMeasure)
                        if(finalMeasure == 1.0) secondUnit ="pound"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "oz", "ounce", "ounces" ->{
                        secondUnit = "ounces"
                        finalMeasure = gramToOunce(firstMeasure)
                        if(finalMeasure == 1.0) secondUnit ="ounce"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                }
            }

            else if(isDistance){
                when (m1) {
                    "m", "meters", "Meters" -> {
                        if(initialMeasure == 1.0) firstUnit = "meter"
                        else firstUnit = "meters"
                        firstMeasure = initialMeasure
                    }
                    "km", "kilometer", "kilometers" ->{
                        if(initialMeasure == 1.0) firstUnit = "kilometer"
                        else firstUnit = "kilometers"
                        firstMeasure = kiloMeterCon(initialMeasure)
                    }
                    "cm", "centimeter", "centimeters" ->{
                        if(initialMeasure == 1.0) firstUnit = "centimeter"
                        else firstUnit = "centimeters"
                        firstMeasure = centMeterCon(initialMeasure)
                    }
                    "mm", "millimeter", "millimeters" ->{
                        if(initialMeasure == 1.0) firstUnit = "millimeter"
                        else firstUnit = "millimeters"
                        firstMeasure = milliMeterCon(initialMeasure)
                    }
                    "yd", "yard", "yards" ->{
                        if(initialMeasure == 1.0) firstUnit = "yard"
                        else firstUnit = "yards"
                        firstMeasure = yardCon(initialMeasure)
                    }
                    "ft", "foot", "feet" ->{
                        firstUnit = "feet"
                        firstMeasure = feetCon(initialMeasure)
                    }
                    "in", "inch", "inches" ->{
                        if(initialMeasure == 1.0) firstUnit = "inch"
                        else firstUnit = "inches"
                        firstMeasure = inchCon(initialMeasure)
                    }
                }

                when (m2) {
                    "m", "meters", "Meters" -> {
                        secondUnit = "meter"
                        finalMeasure = firstMeasure
                        if (finalMeasure > 1.0) secondUnit = "meters"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "km", "kilometer", "kilometers" ->{
                        secondUnit = "kilometers"
                        finalMeasure = meterToKilo(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "kilometer"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "cm", "centimeter", "centimeters" ->{
                        secondUnit = "centimeters"
                        finalMeasure = meterToCenti(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "centimeter"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "mm", "millimeter", "millimeters" ->{
                        secondUnit = "millimeters"
                        finalMeasure = meterToMilli(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "millimeter"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "yd", "yard", "yards" ->{
                        secondUnit = "yards"
                        finalMeasure = meterToYard(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "yard"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "ft", "foot", "feet" ->{
                        secondUnit = "feet"
                        finalMeasure = meterToFeet(firstMeasure)
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "in", "inch", "inches" ->{
                        secondUnit = "inches"
                        finalMeasure = meterToInch(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "inch"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                }
            }

            else if(isTemp){
                when(m1){
                    "degree celsius", "degrees celsius", "celsius", "dc", "c" -> {
                        if(initialMeasure == 1.0) firstUnit = "degree Celsius"
                        else firstUnit = "degrees Celsius"
                        firstMeasure = initialMeasure
                    }
                    "degree fahrenheit", "degrees fahrenheit", "fahrenheit", "df", "f" ->{
                        if(initialMeasure == 1.0) firstUnit = "degree Fahrenheit"
                        else firstUnit = "degrees Fahrenheit"
                        firstMeasure = fahrenToCel(initialMeasure)
                    }
                    "kelvin", "kelvins", "k"->{
                        if(initialMeasure == 1.0) firstUnit = "Kelvin"
                        else firstUnit = "Kelvins"
                        firstMeasure = kelvinToCel(initialMeasure)
                    }
                }

                when(m2){
                    "degree celsius", "degrees celsius", "celsius", "dc", "c" -> {
                        secondUnit = "degrees Celsius"
                        finalMeasure = firstMeasure
                        if (finalMeasure == 1.0) secondUnit = "degree Celsius"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "degree fahrenheit", "degrees fahrenheit", "fahrenheit", "df", "f" ->{
                        secondUnit = "degrees Fahrenheit"
                        finalMeasure = celToFarhren(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "degree Fahrenheit"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                    "kelvin", "kelvins", "k"->{
                        secondUnit = "Kelvins"
                        finalMeasure = celToKelvin(firstMeasure)
                        if (finalMeasure == 1.0) secondUnit = "Kelvin"
                        printResults(initialMeasure,firstUnit,finalMeasure,secondUnit)
                    }
                }
            }
        }
    }while(true)
}

fun printResults(initialMeasure: Double, firstUnit: String, finalMeasure: Double, secondUnit: String){
    println("$initialMeasure $firstUnit is $finalMeasure $secondUnit")
}

//These checker functions will let us know what type of measurement are we dealing with.
fun distanceChecker(arg: String): Boolean{
    when (arg){
        "m", "meters", "Meters" -> return true
        "km", "kilometer", "kilometers" -> return true
        "cm", "centimeter", "centimeters" -> return true
        "mm", "millimeter", "millimeters" -> return true
        "yd", "yard", "yards" -> return true
        "ft", "foot", "feet" -> return true
        "in", "inch", "inches" -> return true
        else -> return false
    }
}

fun weightChecker(arg: String): Boolean{
    when (arg){
        "g", "gram", "grams" -> return true
        "kg", "kilogram", "kilograms" -> return true
        "mg", "milligram", "milligrams" -> return true
        "lb", "pound", "pounds" -> return true
        "oz", "ounce", "ounces" -> return true
        else -> return false
    }
}

fun tempChecker(arg: String): Boolean{
    when (arg){
        "dc", "c", "celsius" -> return true
        "degree celsius", "degrees celsius" -> return true
        "df", "f", "fahrenheit" -> return true
        "degree fahrenheit", "degrees fahrenheit" -> return true
        "kelvin", "kelvins", "k" -> return true
        else -> return false
    }
}

fun unitFinder(arg1: String,arg2: Double): String{
    when(arg1){
        "m", "meters", "Meters" -> {
            if(arg2== 1.0)return "meter"
            else return "meters"
        }
        "km", "kilometer", "kilometers" ->  {
            if(arg2== 1.0)return "kilometer"
            else return "kilometers"
        }
        "cm", "centimeter", "centimeters" ->  {
            if(arg2== 1.0)return "centimeters"
            else if(arg2 < 1.0) return "centimeters"
            else if(arg2 > 1.0)return "centimeters"
            else return "centimeter"
        }
        "mm", "millimeter", "millimeters" ->  {
            if(arg2== 1.0)return "millimeters"
            else if(arg2 < 1.0) return "millimeters"
            else if(arg2 > 1.0)return "millimeters"
            else return "millimeter"
        }
        "yd", "yard", "yards" ->  {
            if(arg2== 1.0)return "yard"
            else return "yards"
        }
        "ft", "foot", "feet" ->  {
            if(arg2== 1.0)return "feet"
            else return "feet"
        }
        "in", "inch", "inches" ->  {
            if(arg2== 1.0)return "inche"
            else return "inches"
        }
        "g", "gram", "grams" ->  {
            if(arg2== 1.0)return "grams"
            else if(arg2 < 1.0) return "grams"
            else if(arg2 > 1.0)return "grams"
            else return "gram"
        }
        "lb", "pound", "pounds" ->  {
            if(arg2== 1.0)return "pounds"
            else return "pounds"
        }
        "kg", "kilogram", "kilograms" ->  {
            if(arg2== 1.0)return "kilogram"
            else return "kilograms"
        }
        "mg", "milligram", "milligrams" ->  {
            if(arg2== 1.0)return "milligram"
            else return "milligrams"
        }
        "oz", "ounce", "ounces" ->  {
            if(arg2== 1.0)return "ounce"
            else return "ounces"
        }
        "dc", "c", "celsius", "degree celsius", "degrees celsius"  -> {
            if(arg2== 1.0)return "degrees Celsius"
            else if(arg2 < 1.0) return "degrees Celsius"
            else if(arg2 > 1.0)return "degrees Celsius"
            else return "degree Celsius"
        }
        "df", "f", "fahrenheit", "degree fahrenheit", "degrees fahrenheit"  -> {
            if(arg2== 1.0)return "degrees Fahrenheit"
            else if(arg2 < 1.0) return "degrees Fahrenheit"
            else if(arg2 > 1.0)return "degrees Fahrenheit"
            else return "degree Fahrenheit"
        }
        "kelvin", "kelvins", "k" -> {
            if(arg2 == 1.0)return "Kelvins"
            else if(arg2 < 1.0) return "Kelvins"
            else if(arg2 > 1.0)return "Kelvins"
            else return "Kelvin"
        }
        else -> return "???"
    }
}

//All of the following functions will convert any intial weight to grams first.
fun kilCon(arg: Double):Double{
    return arg*1000.0
}

fun milliCon(arg: Double):Double{
    return arg/1000.0
}

fun poundCon(arg: Double):Double{
    return arg*453.592
}

fun ounceCon(arg: Double):Double{
    return arg*28.3495
}

//Functions that take a weight in grams and return some other weight.
fun gramToKilo(arg: Double):Double{
    return arg/1000.0
}

fun gramToMilli(arg: Double):Double{
    return arg*1000.0
}

fun gramToPound(arg: Double):Double{
    return arg/453.592
}

fun gramToOunce(arg: Double):Double{
    return arg/28.3495
}

//Converts any initial distance measurement into meters.
fun kiloMeterCon(arg:Double):Double {
    return arg * 1000
}

fun centMeterCon(arg:Double):Double {
    return arg * 0.01
}

fun milliMeterCon(arg:Double):Double {
    return arg * 0.001
}

fun mileCon(arg:Double):Double {
    return arg * 1609.35
}

fun yardCon(arg:Double):Double{
    return arg * 0.9144
}

fun feetCon(arg:Double):Double {
    return arg * 0.3048
}

fun inchCon(arg:Double):Double {
    return arg * 0.0254
}

//Converts any meter measurement into some other distance measure.
fun meterToKilo(arg:Double):Double{
    return arg/1000
}

fun meterToCenti(arg:Double):Double{
    return arg*100
}

fun meterToMilli(arg:Double):Double{
    return arg*1000
}

fun meterToMile(arg:Double):Double{
    return arg/1609.344
}

fun meterToYard(arg:Double):Double{
    return arg/0.9144
}

fun meterToFeet(arg:Double):Double{
    return arg/0.3048
}

fun meterToInch(arg:Double):Double{
    return arg / 0.0254
}

//Any temperature to Celsius Functions
fun kelvinToCel(arg: Double):Double{
    return arg - 273.15
}

fun fahrenToCel(arg: Double):Double{
    var temp = (arg - 32.0) * 5.0/9.0
    return temp
}

// Celsius to any other tempt
fun celToKelvin(arg: Double):Double{
    return arg + 273.15
}

fun celToFarhren(arg: Double):Double{
    var temp = (arg * 9.0/5.0) + 32.0
    return temp
}