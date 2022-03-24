/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class JavaApplication {
    public static void main(String[] args) {
        User user = new User();

        user.setFirstName("Testy");
        user.setLastName("McTesterson");

        // 7. Making your Kotlin Code Java-Friendly
        // You need to generate a secondary constructor because java doesn't handle the default parameters as Kotlin does
        // but, you could do something easier
        // use @JvmOverloads in the address constructor
        Address address = new Address("345 Nonexistent Avenue NW", null,
                "Washington", "DC", "20016", AddressType.Shipping);

        // address.setAddressType(AddressType.Billing);

        UserExtensions.addOrUpdateAddress(user, address);
        LabelPrinter.printLabelFor(user);

        // 8. Accessing kotlin nested objects
        // in kotlin you can have companion objects inside a class that works as a static object
        // but for Java to access those objects they must be instantiated

        Address.JSONKeys keys = Address.JSONKeys.INSTANCE;

        HashMap<String, Object> addressJSON = new HashMap<>();
        //addressJSON.put(keys.getStreetLine1(), address.getStreetLine1());

        // its works but is too noisy with all those getters, lets tell the compiler to not generate them
        // with 'const' in object attributes and @JvmField in class attributes
        addressJSON.put(keys.streetLine1 , address.streetLine1);
        addressJSON.put(keys.streetLine2 , address.streetLine2);
        addressJSON.put(keys.stateOrProvince , address.stateOrProvince);
        addressJSON.put(keys.postalCode , address.postalCode);
        addressJSON.put(keys.country , address.country);
        addressJSON.put(keys.addressType , address.addressType.name());


        System.out.println("Address JSON: \n" + addressJSON);

        System.out.println("Sample first line: " + Address.sampleFirstLine);

        // functions must be accessed from the 'Companion' object because functions cannot be const as values
        // you can add @JvmStatic to the Address.canadianSample() to avoid the 'Companion' declaration
        //System.out.println("Sample canadian address: " + Address.Companion.canadianSample(AddressType.Billing));
        System.out.println("Sample canadian address: " + Address.canadianSample(AddressType.Billing));


        CreditCard creditCard1 = new CreditCard("1234 1234 1234 1234", "01", "24");
        CreditCard creditCard2 = new CreditCard("1234 1234 1234 1111", "01", "24");
        CreditCard creditCard3 = new CreditCard("1234 1234 1234 1234", "01", "21");
        CreditCard creditCard4 = new CreditCard("1234 1234 1234 1234", "01", "24");

        ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>(Arrays.asList(creditCard1, creditCard2, creditCard3, creditCard4));

        for (CreditCard creditCard : creditCards){
            try{
                user.addCreditCard(creditCard);

            } catch(Exception ex){
                System.err.print(ex.getMessage());
            }
        }

        System.out.println("Credit cards: \n" + user.creditCards.toString());

    }
}
