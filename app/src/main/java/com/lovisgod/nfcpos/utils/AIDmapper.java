package com.lovisgod.nfcpos.utils;

import android.os.RemoteException;

import com.lovisgod.nfcpos.data.model.AID;
import com.lovisgod.nfcpos.data.model.CapkV2;

public class AIDmapper {

    public void getAllCapk(EmvOptV2 emvOptV2) throws RemoteException {
        emvOptV2.addCapk(getMasterCapk1());
        emvOptV2.addCapk(getMasterCapk2());
        emvOptV2.addCapk(getMasterCapk3());
        emvOptV2.addCapk(getMasterCapk4());
        emvOptV2.addCapk(getMasterCapk5());
        emvOptV2.addCapk(getMasterCapk6());
        emvOptV2.addCapk(getMasterCapk7());

        // VISA

        emvOptV2.addCapk(getVisaCapk1());
        emvOptV2.addCapk(getVisaCapk2());
        emvOptV2.addCapk(getVisaCapk3());
        emvOptV2.addCapk(getVisaCapk4());

        // VERVE

        emvOptV2.addCapk(getVerveCapk1());
        emvOptV2.addCapk(getVerveCapk2());
        emvOptV2.addCapk(getVerveCapk3());
        emvOptV2.addCapk(getVerveCapk4());

    }


    public void getAllAid(EmvOptV2 emvOptV2) throws Exception {

        // MASTER
        emvOptV2.addAid(getMasterAid());

        // VISA
        emvOptV2.addAid(getVisa1Aid());
        emvOptV2.addAid(getVisa2Aid());

        // VERVE

        emvOptV2.addAid(getVerveAid());
        emvOptV2.addAid(getVerve1Aid());


    }



    public AID getVerveAid() {
        System.out.println("this got called for getting verve AID");
        AID aidV2 = new AID();
        aidV2.aid = ByteUtil.hexStr2Bytes("A0000003710001");
        aidV2.TACDenial = ByteUtil.hexStr2Bytes("0400000000");
        aidV2.TACDefault = ByteUtil.hexStr2Bytes("fc50aca000");
        aidV2.TACOnline = ByteUtil.hexStr2Bytes("f850acf800");
        aidV2.dDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.tDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.selFlag = 0X00;
        aidV2.floorLimit = ByteUtil.hexStr2Bytes("10000");
        aidV2.termOfflineFloorLmt = ByteUtil.hexStr2Bytes("10000");
        aidV2.termClssOfflineFloorLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.cvmLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.termClssLmt = ByteUtil.hexStr2Bytes("999999999");
        aidV2.kernelID= ByteUtil.hexStr2Bytes("30");
        aidV2.referCurrCode = ByteUtil.hexStr2Bytes("0566");
        aidV2.referCurrExp = ByteUtil.hexStr2Byte("02");
        aidV2.version = ByteUtil.hexStr2Bytes("0096");
        aidV2.maxTargetPer = Integer.valueOf(80).byteValue();
        aidV2.targetPer = Integer.valueOf(80).byteValue();
        aidV2.threshold = ByteUtil.hexStr2Bytes("100");

        return aidV2;

    }

    public AID getVerve1Aid() {
        System.out.println("this got called for getting verve 1 AID");
        AID aidV2 = new AID();
        aidV2.aid = ByteUtil.hexStr2Bytes("A0000003710002");
        aidV2.TACDenial = ByteUtil.hexStr2Bytes("0400000000");
        aidV2.TACDefault = ByteUtil.hexStr2Bytes("fc50aca000");
        aidV2.TACOnline = ByteUtil.hexStr2Bytes("f850acf800");
        aidV2.dDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.tDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.selFlag = 0X00;
        aidV2.floorLimit = ByteUtil.hexStr2Bytes("10000");
        aidV2.termOfflineFloorLmt = ByteUtil.hexStr2Bytes("10000");
        aidV2.termClssOfflineFloorLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.cvmLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.termClssLmt = ByteUtil.hexStr2Bytes("999999999");
        aidV2.kernelID= ByteUtil.hexStr2Bytes("31");
        aidV2.referCurrCode = ByteUtil.hexStr2Bytes("0566");
        aidV2.referCurrExp = ByteUtil.hexStr2Byte("02");
        aidV2.version = ByteUtil.hexStr2Bytes("0001");
        aidV2.maxTargetPer = Integer.valueOf(80).byteValue();
        aidV2.targetPer = Integer.valueOf(80).byteValue();
        aidV2.threshold = ByteUtil.hexStr2Bytes("100");

        return aidV2;

    }


    public CapkV2 getVerveCapk1(){
        System.out.println("this got called for getting verve capk1");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("319F3C608B67F1118C729B0E1516EAB07CB290C8");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311217");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("03");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000371");
        capkV2.modul = ByteUtil.hexStr2Bytes("d06238b856cf2c8890a7f668ca17c19247498d193a7c11e7105dedeee6a873e8189e50493e9b17547c42ea4fa88bbef30bb6bc2409246ccc95f36622a7f4d92d46444f20b1b24bf63c5b28395d8ef18c23205c2119dfe5fba2fbfc311b2fe8a6a75b35a7dab72d421792a500cdfd8133b8a97d84a49c0bd22d52d06ea5e0ef3e471d47d8370c37aa48b564689d0035d9");
        return capkV2;
    }

    public CapkV2 getVerveCapk2(){
        System.out.println("this got called for getting verve capk2");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("F5BAB84ECE5F8BD45511E5CA861B80C7E6C51F55");
        capkV2.expDate = ByteUtil.hexStr2Bytes("281228");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("06");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000371");
        capkV2.modul = ByteUtil.hexStr2Bytes("D2DA0134B4DFC93A75EE8960C99896D50A91527B87BA7B16CDB77E5B6FDB750EB70B54026CADDA1D562C77A2C6DA541E94BC415D43E68489B16980F2E887C09E4CF90E2E639B179277BBA0E982CCD1F80521D1457209125B3ABCD309E1B92B5AEDA2EB1CBF933BEAD9CE7365E52B7D17FCB405AA28E5DE6AA3F08E764F745E70859ABCBA41E570A6E4367B3D6FECE723B73ABF3EB53DCDE3816E8A813460447021509D0DFDF2EEEE74CC35485FB55C26836EB3BF9C7DEBEE6C0B77B7BE059233801CF76B321FCA25FB1E63117AE1865E23161EC39D7B1FB84256C2BE72BF8EC771548DB9F00BEF77C509FADA15E2B53FF950D383F96211D3");
        return capkV2;
    }

    public CapkV2 getVerveCapk3(){
        System.out.println("this got called for getting verve capk3");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("8B36A3E3D814CE6C6EBEAAF27674BB7BC67275B1");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311217");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("04");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000371");
        capkV2.modul = ByteUtil.hexStr2Bytes("D13CD5E1B921E4E0F0D40E2DE14CCE73E3A34ED2DCFA826531D8195641091E37C8474D19B686E8243F089A69F7B18D2D34CB4824F228F7750F96D1EFBDFF881F259A8C04DE64915A3A3D7CB846135F4083C93CDE755BC808886F600542DFF085558D5EA7F45CB15EC835064AA856D602A0A44CD021F54CF8EC0CC680B54B3665ABE74A7C43D02897FF84BB4CB98BC91D");
        return capkV2;
    }

    public CapkV2 getVerveCapk4(){
        System.out.println("this got called for getting verve capk4");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("676822D335AB0D2C3848418CB546DF7B6A6C32C0");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20241231");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("05");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000371");
        capkV2.modul = ByteUtil.hexStr2Bytes("B036A8CAE0593A480976BFE84F8A67759E52B3D9F4A68CCC37FE720E594E5694CD1AE20E1B120D7A18FA5C70E044D3B12E932C9BBD9FDEA4BE11071EF8CA3AF48FF2B5DDB307FC752C5C73F5F274D4238A92B4FCE66FC93DA18E6C1CC1AA3CFAFCB071B67DAACE96D9314DB494982F5C967F698A05E1A8A69DA931B8E566270F04EAB575F5967104118E4F12ABFF9DEC92379CD955A10675282FE1B60CAD13F9BB80C272A40B6A344EA699FB9EFA6867");
        return capkV2;
    }

    // master card AID and CAPK
    //AID
    public AID getMasterAid() {
        System.out.println("this got called for getting master AID");
        AID aidV2 = new AID();
        aidV2.aid = ByteUtil.hexStr2Bytes("A0000000041010");
        aidV2.TACDenial = ByteUtil.hexStr2Bytes("0000000000");
        aidV2.TACDefault = ByteUtil.hexStr2Bytes("CC00FC8000");
        aidV2.TACOnline = ByteUtil.hexStr2Bytes("CC00FC8000");
        aidV2.dDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.tDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.selFlag = 0X00;
        aidV2.floorLimit = ByteUtil.hexStr2Bytes("10000");
        aidV2.termOfflineFloorLmt = ByteUtil.hexStr2Bytes("10000");
        aidV2.termClssOfflineFloorLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.cvmLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.termClssLmt = ByteUtil.hexStr2Bytes("999999999");
        aidV2.kernelID= ByteUtil.hexStr2Bytes("02");
        aidV2.referCurrCode = ByteUtil.hexStr2Bytes("0566");
        aidV2.referCurrExp = ByteUtil.hexStr2Byte("02");
        aidV2.version = ByteUtil.hexStr2Bytes("0001");
        aidV2.maxTargetPer = Integer.valueOf(80).byteValue();
        aidV2.targetPer = Integer.valueOf(80).byteValue();
        aidV2.threshold = ByteUtil.hexStr2Bytes("100");

        return aidV2;

    }
    //CAPK
    public CapkV2 getMasterCapk1(){
        System.out.println("this got called for getting master capk1");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("8BB99ADDF7B560110955014505FB6B5F8308CE27");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("00");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000004");
        capkV2.modul = ByteUtil.hexStr2Bytes("9E15214212F6308ACA78B80BD986AC287516846C8D548A9ED0A42E7D997C902C3E122D1B9DC30995F4E25C75DD7EE0A0CE293B8CC02B977278EF256D761194924764942FE714FA02E4D57F282BA3B2B62C9E38EF6517823F2CA831BDDF6D363D");
        return capkV2;
    }

    public CapkV2 getMasterCapk2(){
        System.out.println("this got called for getting master capk2");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("EA950DD4234FEB7C900C0BE817F64DE66EEEF7C4");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("01");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000004");
        capkV2.modul = ByteUtil.hexStr2Bytes("D2010716C9FB5264D8C91A14F4F32F8981EE954F20087ED77CDC5868431728D3637C632CCF2718A4F5D92EA8AB166AB992D2DE24E9FBDC7CAB9729401E91C502D72B39F6866F5C098B1243B132AFEE65F5036E168323116338F8040834B98725");
        return capkV2;
    }

    public CapkV2 getMasterCapk3(){
        System.out.println("this got called for getting master capk3");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("AF1CC1FD1C1BC9BCA07E78DA6CBA2163F169CBB7");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("02");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000004");
        capkV2.modul = ByteUtil.hexStr2Bytes("CF4264E1702D34CA897D1F9B66C5D63691EACC612C8F147116BB22D0C463495BD5BA70FB153848895220B8ADEEC3E7BAB31EA22C1DC9972FA027D54265BEBF0AE3A23A8A09187F21C856607B98BDA6FC908116816C502B3E58A145254EEFEE2A3335110224028B67809DCB8058E24895");
        return capkV2;
    }

    public CapkV2 getMasterCapk4(){
        System.out.println("this got called for getting master capk4");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("5ADDF21D09278661141179CBEFF272EA384B13BB");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("03");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000004");
        capkV2.modul = ByteUtil.hexStr2Bytes("C2490747FE17EB0584C88D47B1602704150ADC88C5B998BD59CE043EDEBF0FFEE3093AC7956AD3B6AD4554C6DE19A178D6DA295BE15D5220645E3C8131666FA4BE5B84FE131EA44B039307638B9E74A8C42564F892A64DF1CB15712B736E3374F1BBB6819371602D8970E97B900793C7C2A89A4A1649A59BE680574DD0B60145");
        return capkV2;
    }

    public CapkV2 getMasterCapk5(){
        System.out.println("this got called for getting master capk5");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("381A035DA58B482EE2AF75F4C3F2CA469BA4AA6C");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("04");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000004");
        capkV2.modul = ByteUtil.hexStr2Bytes("A6DA428387A502D7DDFB7A74D3F412BE762627197B25435B7A81716A700157DDD06F7CC99D6CA28C2470527E2C03616B9C59217357C2674F583B3BA5C7DCF2838692D023E3562420B4615C439CA97C44DC9A249CFCE7B3BFB22F68228C3AF13329AA4A613CF8DD853502373D62E49AB256D2BC17120E54AEDCED6D96A4287ACC5C04677D4A5A320DB8BEE2F775E5FEC5");
        return capkV2;
    }

    public CapkV2 getMasterCapk6(){
        System.out.println("this got called for getting master capk6");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("EBFA0D5D06D8CE702DA3EAE890701D45E274C845");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("05");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000371");
        capkV2.modul = ByteUtil.hexStr2Bytes("B8048ABC30C90D976336543E3FD7091C8FE4800DF820ED55E7E94813ED00555B573FECA3D84AF6131A651D66CFF4284FB13B635EDD0EE40176D8BF04B7FD1C7BACF9AC7327DFAA8AA72D10DB3B8E70B2DDD811CB4196525EA386ACC33C0D9D4575916469C4E4F53E8E1C912CC618CB22DDE7C3568E90022E6BBA770202E4522A2DD623D180E215BD1D1507FE3DC90CA310D27B3EFCCD8F83DE3052CAD1E48938C68D095AAC91B5F37E28BB49EC7ED597");
        return capkV2;
    }

    public CapkV2 getMasterCapk7(){
        System.out.println("this got called for getting master capk7");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("F910A1504D5FFB793D94F3B500765E1ABCAD72D9");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("06");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000371");
        capkV2.modul = ByteUtil.hexStr2Bytes("CB26FC830B43785B2BCE37C81ED334622F9622F4C89AAE641046B2353433883F307FB7C974162DA72F7A4EC75D9D657336865B8D3023D3D645667625C9A07A6B7A137CF0C64198AE38FC238006FB2603F41F4F3BB9DA1347270F2F5D8C606E420958C5F7D50A71DE30142F70DE468889B5E3A08695B938A50FC980393A9CBCE44AD2D64F630BB33AD3F5F5FD495D31F37818C1D94071342E07F1BEC2194F6035BA5DED3936500EB82DFDA6E8AFB655B1EF3D0D7EBF86B66DD9F29F6B1D324FE8B26CE38AB2013DD13F611E7A594D675C4432350EA244CC34F3873CBA06592987A1D7E852ADC22EF5A2EE28132031E48F74037E3B34AB747F");
        return capkV2;
    }

    // VISA AID and CAPK


    //AID
    public AID getVisa1Aid() {
        System.out.println("this got called for getting Visa1 AID");
        AID aidV2 = new AID();
        aidV2.aid = ByteUtil.hexStr2Bytes("A0000000031010");
        aidV2.TACDenial = ByteUtil.hexStr2Bytes("0010000000");
        aidV2.TACDefault = ByteUtil.hexStr2Bytes("DC4000A800");
        aidV2.TACOnline = ByteUtil.hexStr2Bytes("DC4000A800");
        aidV2.dDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.tDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.selFlag = 0X00;
        aidV2.floorLimit = ByteUtil.hexStr2Bytes("10000");
        aidV2.termOfflineFloorLmt = ByteUtil.hexStr2Bytes("10000");
        aidV2.termClssOfflineFloorLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.cvmLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.termClssLmt = ByteUtil.hexStr2Bytes("999999999");
        aidV2.kernelID= ByteUtil.hexStr2Bytes("02");
        aidV2.referCurrCode = ByteUtil.hexStr2Bytes("0566");
        aidV2.referCurrExp = ByteUtil.hexStr2Byte("02");
        aidV2.version = ByteUtil.hexStr2Bytes("0001");
        aidV2.maxTargetPer = Integer.valueOf(80).byteValue();
        aidV2.targetPer = Integer.valueOf(80).byteValue();
        aidV2.threshold = ByteUtil.hexStr2Bytes("100");

        return aidV2;

    }

    public AID getVisa2Aid() {
        System.out.println("this got called for getting Visa2 AID");
        AID aidV2 = new AID();
        aidV2.aid = ByteUtil.hexStr2Bytes("A0000000032010");
        aidV2.TACDenial = ByteUtil.hexStr2Bytes("0010000000");
        aidV2.TACDefault = ByteUtil.hexStr2Bytes("DC4000A800");
        aidV2.TACOnline = ByteUtil.hexStr2Bytes("DC4000A800");
        aidV2.dDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.tDOL = ByteUtil.hexStr2Bytes("9F3704");
        aidV2.selFlag = 0X00;
        aidV2.floorLimit = ByteUtil.hexStr2Bytes("10000");
        aidV2.termOfflineFloorLmt = ByteUtil.hexStr2Bytes("10000");
        aidV2.termClssOfflineFloorLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.cvmLmt = ByteUtil.hexStr2Bytes("0");
        aidV2.termClssLmt = ByteUtil.hexStr2Bytes("999999999");
        aidV2.kernelID= ByteUtil.hexStr2Bytes("02");
        aidV2.referCurrCode = ByteUtil.hexStr2Bytes("0566");
        aidV2.referCurrExp = ByteUtil.hexStr2Byte("02");
        aidV2.version = ByteUtil.hexStr2Bytes("0001");
        aidV2.maxTargetPer = Integer.valueOf(80).byteValue();
        aidV2.targetPer = Integer.valueOf(80).byteValue();
        aidV2.threshold = ByteUtil.hexStr2Bytes("100");

        return aidV2;

    }

    public CapkV2 getVisaCapk1(){
        System.out.println("this got called for getting visa capk1");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("D34A6A776011C7E7CE3AEC5F03AD2F8CFC5503CC");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("01");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000003");
        capkV2.modul = ByteUtil.hexStr2Bytes("C696034213D7D8546984579D1D0F0EA519CFF8DEFFC429354CF3A871A6F7183F1228DA5C7470C055387100CB935A712C4E2864DF5D64BA93FE7E63E71F25B1E5F5298575EBE1C63AA617706917911DC2A75AC28B251C7EF40F2365912490B939BCA2124A30A28F54402C34AECA331AB67E1E79B285DD5771B5D9FF79EA630B75");
        return capkV2;
    }

    public CapkV2 getVisaCapk2(){
        System.out.println("this got called for getting visa capk2");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("B4BC56CC4E88324932CBC643D6898F6FE593B172");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("07");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000003");
        capkV2.modul = ByteUtil.hexStr2Bytes("A89F25A56FA6DA258C8CA8B40427D927B4A1EB4D7EA326BBB12F97DED70AE5E4480FC9C5E8A972177110A1CC318D06D2F8F5C4844AC5FA79A4DC470BB11ED635699C17081B90F1B984F12E92C1C529276D8AF8EC7F28492097D8CD5BECEA16FE4088F6CFAB4A1B42328A1B996F9278B0B7E3311CA5EF856C2F888474B83612A82E4E00D0CD4069A6783140433D50725F");
        return capkV2;
    }

    public CapkV2 getVisaCapk3(){
        System.out.println("this got called for getting visa capk3");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("20D213126955DE205ADC2FD2822BD22DE21CF9A8");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("08");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000003");
        capkV2.modul = ByteUtil.hexStr2Bytes("D9FD6ED75D51D0E30664BD157023EAA1FFA871E4DA65672B863D255E81E137A51DE4F72BCC9E44ACE12127F87E263D3AF9DD9CF35CA4A7B01E907000BA85D24954C2FCA3074825DDD4C0C8F186CB020F683E02F2DEAD3969133F06F7845166ACEB57CA0FC2603445469811D293BFEFBAFAB57631B3DD91E796BF850A25012F1AE38F05AA5C4D6D03B1DC2E568612785938BBC9B3CD3A910C1DA55A5A9218ACE0F7A21287752682F15832A678D6E1ED0B");
        return capkV2;
    }

    public CapkV2 getVisaCapk4(){
        System.out.println("this got called for getting visa capk4");
        CapkV2 capkV2 = new CapkV2();
        capkV2.arithInd = ByteUtil.hexStr2Byte("01");
        capkV2.checkSum = ByteUtil.hexStr2Bytes("1FF80A40173F52D7D27E0F26A146A1C8CCB29046");
        capkV2.expDate = ByteUtil.hexStr2Bytes("20311221");
        capkV2.exponent = ByteUtil.hexStr2Bytes("03");
        capkV2.hashInd = ByteUtil.hexStr2Byte("01");
        capkV2.index = ByteUtil.hexStr2Byte("09");
        capkV2.rid = ByteUtil.hexStr2Bytes("A000000003");
        capkV2.modul = ByteUtil.hexStr2Bytes("9D912248DE0A4E39C1A7DDE3F6D2588992C1A4095AFBD1824D1BA74847F2BC4926D2EFD904B4B54954CD189A54C5D1179654F8F9B0D2AB5F0357EB642FEDA95D3912C6576945FAB897E7062CAA44A4AA06B8FE6E3DBA18AF6AE3738E30429EE9BE03427C9D64F695FA8CAB4BFE376853EA34AD1D76BFCAD15908C077FFE6DC5521ECEF5D278A96E26F57359FFAEDA19434B937F1AD999DC5C41EB11935B44C18100E857F431A4A5A6BB65114F174C2D7B59FDF237D6BB1DD0916E644D709DED56481477C75D95CDD68254615F7740EC07F330AC5D67BCD75BF23D28A140826C026DBDE971A37CD3EF9B8DF644AC385010501EFC6509D7A41");
        return capkV2;
    }
}
