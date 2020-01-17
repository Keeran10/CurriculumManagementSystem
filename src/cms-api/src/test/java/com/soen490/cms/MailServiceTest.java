package com.soen490.cms;

import com.soen490.cms.Models.User;
import com.soen490.cms.Services.MailService;
import com.soen490.cms.Services.PdfService.PdfService;
import com.soen490.cms.Services.SearchService;
import org.springframework.mail.javamail.JavaMailSender;
import org.junit.Before;
import org.junit.Test;

import javax.mail.internet.MimeMessage;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MailServiceTest {

    User user;
    MailService mailService;
    JavaMailSender javaMailSender =  mock(JavaMailSender.class);
    PdfService pdfService =  mock(PdfService.class);
    SearchService searchService =  mock(SearchService.class);


    @Before
    public void initiation(){
        user = new User();
        user.setId(1);
        user.setEmail("boris@soen.com");
        mailService = new MailService();

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(pdfService.getPDF(1)).thenReturn(pdfString.getBytes());
        when(searchService.findUserById(1)).thenReturn(user);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailService.setServiceMock(javaMailSender, pdfService, searchService);
    }

    @Test
    public void sendMailServiceTest(){
     boolean mailSentWithSuccess = mailService.sendMailService(1, user);

     assertTrue(mailSentWithSuccess);
    }

    String pdfString = "%PDF-1.4\n" +
            "%����\n" +
            "2 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Roman/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "3 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "4 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "5 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "6 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "7 0 obj\n" +
            "<</Length 1553/Filter/FlateDecode>>stream\n" +
            "x��X]S�8\u0014}ϯ�O;t\u0006�%\u007F�a�h\blw\u0017�B��Sv:�V\u0012����N(�h��^�Nbl�|\f� ��{ϹWҹ�] pD��`\u0014��8\u001B�\u001C��\u000Eh A\u0014�4\u0019��\u001D��s\u0002��t68�t:���b��������\u001By7��C(\u0010j��n���������8\u001E\u007F�r}3��\u001F�W\u0017�\u0013�|nao>N� �������R\u0016<��|�\u0013��%+Vr8�\u0013��4�+�D�JW\u0019�\u0017,��J�R'\u0018٢d)�\u0013�`&\u0015��,ᙈ�\u001F�T�\u000Bu�{�\u007FL�G��ڻ�\f����l���%+���R�������=�\u0006�}\u001B\u007F{�u�0����&^H��<���\u0011q� ph����&ą�\u0019�e� �\u001D�\u0019L��9W\"�\u0003��e�\\�\\�\u0010��?gv\u001B�����R\u0007�%EB\u001A:��\"�\u0006���F��{�x��\u000By�z�E�uI�ȏ�a;3o�x���.�\u0017\u0012�;\u0015Gp�\n" +
            "\u001D5N�\n" +
            "\u001Cn\u000F.�y��9,\u0010%�\u001F'\u001CvP\u07B3x�Sه����Qg�a��\u0014��}eK�v�~bs\u000EW�쎫Nb�ш:����!q��3|��x����t�\u0019uS�R�\u0013;.\u0006�\u0013���Q�w�\u001B�@\t(>��;|l�{Є�\u001D�u�6{�Ј�\"\u0018�(���\u0011�������g-�Z\u0001\\�\t\u001E��\u0018\\�x��\u0002�\u0019L��R'\u0002�n��4��T2�4�,�u�\br\t��I�h\u0004� Bz�yN5W̤\u001E�2���`��,h@\u001D:z�\u0004�)܀P��$\n" +
            "���z�\u0011m��Jˣn4MZ\u0019�u�>fuW�t��(5\u001F\u001Cx�_��9�\f�#7[�T�\u000BQ�6XaC��\u0019/�<oy����N�m�����\u0001�y�\u001B�`�W<\u0011eQm��A\u0015\u000F1����\u0002P��J\u0014H�\u0004���p|\u001BR3.�\u0002��\\��\u0017��d���X\u0013���W�\u000EߴLl��\u0016�����I�Ҷ��|@�(>\"��U��(NQtʂ+D<��=`p�!,Œ\u0017Hf&R����\u0002LG-`�FOw�0�JƼ( �ʋ�����fX\u001B��T?:�X�҇B\u0014fX�� r�]\"\u001F\u001Br�B�\u0017�\u0002̤(S�\u000B�\u0014Ƣ��\u000E\\��0�\n" +
            "�k�[�0��\u0011�Ĭ1���T�����\u0016�f|�\u0017l-d��%��L�\u0015�\u0003�JfV$(%>�L�<�_9p&3&r\u001Bn����4}�^A�����D�a�f�:\u000B��%W����jËRdU���p�\u0016n�&��d�=$ �#�Tz%�\u000E�A\u0014��\u0019��]�<�Iƺ\n" +
            "\u000B�YaC�[�H\u0019�9���t��Xz\u00022�\u001C��s�\t��r�\u00152?\u0018V\u0002룐�\u0011�@�4�B�6ٍ�\u0006�G����`uu|R�\f�49\u001A>e\u0012��RX�\u0012E\u000E.n�أ�����j�N��#X4v7`;}�Aϓ�~�ݹ�_��&�\u0017H�\u000E\b����ϋ|x\u001Bյ�k\u000B�c�o!�6؎̶�\u0019�Bw��m��h3���Z��*k\u0011gx\u0013嵢����:��\u05CA�Vؽ�\f��^+d[^{�\u0019^%��S�\u0016�J\u0012\u0003ǥ�y�\u0001�n\u052D�3�&\u0011,��{�A��]u}ٝ\u0016�\u0001��ϱ�8\uE341DԜ��.\u000B��?\b\u0012�ź�\u0005�\n" +
            "�V\u007F��,V[@\u0004�Mo@$${\u0003ڀ�\u0006H\u007Fpx����>�M�'8\u007F/.Ƅ�\n" +
            "7��kA��K�Jk|xc鏏���|T\u0002/�fC�����w\\�/nK�9��\u0017��\u0003s��U���k&��1կGԍ<ǫ?�t\t\u001EA�eb�\u0017�2M5G\u0014J�Ɲj\u0018�2�/$K�O�\u001D\u0005�i��\uEBF1D�B\u007F}�y'�Vf������B�Kj�8��w;},I\u001E'��\u0016y�VH^A\u000B%�o\teb��a8��|�i���z��{\n" +
            "endstream\n" +
            "endobj\n" +
            "1 0 obj\n" +
            "<</Type/Page/MediaBox[0 0 595 842]/Resources<</Font<</F1 2 0 R/F2 3 0 R/F3 4 0 R/F4 5 0 R/F5 6 0 R>>>>/Rotate 90/Contents 7 0 R/Annots 8 0 R/Parent 9 0 R>>\n" +
            "endobj\n" +
            "11 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Roman/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "12 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "13 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "14 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "15 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "16 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Oblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "17 0 obj\n" +
            "<</Length 1275/Filter/FlateDecode>>stream\n" +
            "x��V�R�H\u0014��)�ՖViLw\u0012�x����*\n" +
            "x�5nM5�\u0011z�\u000F�I`�\u0017�G����\t ����\\@��t\u007F���c\u0002�S\u0006&8�C�A���u9lq\u0007\u001Cρa�2I��\u001Fg�\f\u0018��k������M�\u001B;�~��;\u0018~���?�\b\u0007��#��&!nB4�][+�N�?��U��;��=�C��&\u001E��\u000F���S�\u001EU:M3\fwX��\u0010�X��\u00109B�PJ\u0006ET�Й�d��\u0001���\n" +
            "�o�LD��B�k��\"\u0010!�2��Q�\u001Dznr����?]\".�m�e\n" +
            "��4�\u0018�\\�2M�>M��Y#��X�&Xb�x{=t-��|q6\b&i\u001A�o�������\u0018��c�e��F&\u0002:i���\u001E���M�2AT2\u0019�HB\u0012��\"GEG$&\u0001�H@7\u0014�©P�6xW)��mójJ�\t�\u0017\u0018���\\(�T�zP\n" +
            "Q�ĻJp��\f�\uE65F�x�c��Kh���\u001B�K\u0011L0���\u00104����%-_��Y�I�gcs�*�G1Fx(�\u0011�\u001D}۾�\n" +
            "�����e�k\u001A��)o�[��;l=Q\u000B�?\u000E_Hp�2\n" +
            "\u0007溣YN\u001B,�\n" +
            "����`)�i�,�+|)`�Y��G�/1\u001B\\���k\u0002��f�W]�j�I�`]�&ug�\u0004\n" +
            ".\f���Ì�\u001F��=�~ �7�ؼ�x��w{.i\u001E{y�&�\u001A\u001A��چ�\"�1�_�\n" +
            "uά\u0003V��\u0012Q���ˑmЫ�0�y�r\\�l��i���wH� ���6�U\u0011��\u0013�\u0012�(\u001B�mR�R��N6W0�\u001C��\u001C�\u000E\u001E)�#�a\u00043��&n^�2\tV�b���\u0004D�a<�\u0016\u0010Q�/tVW�E�\u0006�c��\u0005d�C2�\u0007�\u0017�Z�&:V�\u000Be�FVL$*���B�i��$f\u0006ܞ� Uc�ȿ˶~R�8�dc�|�RA(g�2�/N��&f��,\u001321H�\u0004+��)t\u001E�K�� C��HdP=)#����=�@��\u001Aȸ_#�N\u0016\u0019�D��\fx �T�U6�\u0001\n" +
            "��2��9\u0001S��IZ\u0010ϔbA�o\u0006\f��\\#\"���&b�F\\\u0007�\u0013�T\t\u0002Y�\u0010u�&HwY\u0011�U�?��s\u0018�EH���|B0b�T�\u0001R �e���|�w���\u0007bzu��\u001C&ސ��\u0019�M\u0528Oi�Mƪn�*\u0018cOq[~�ֺGn��'�[�,�W>��fȨ�h\u001Eۄ��:����H�#�Z��\u0015��+��w�L\u000E\u001AmΛ|�\u000F)�\u000F0\u007F\u001Fp�rOL\uF20E8�H�\bu��\"9\u0006F\u000B\u0010:uF4k��I����N��q��:��N`>Ŧ�,fP�\u058B�^`�^e����4S�1lY0Y�G��\u001E�\bs�}$jE\"7H\u000E\u001BG\u000F\u0014\u0019G��o��\u0003�9{y�&\u0011��\u0014��L�>NS�7�g;\u001F�g9�xzJ��A�\u000E�\tGw�\u0011%@\u001A�W�g�\u001F\u001Fe^�\u001877_���f��V��)7=˰��㮂�P�^+\\��(�:\u0016Ӑ|\u0013V\u001ASN�'�e�\u000F�e�\u0003��{KTOp����-�����m�trP-���\u000BK�\u0003\u0015�8�m\u0007\u001ET�lT�j�?\u007F��W\n" +
            "endstream\n" +
            "endobj\n" +
            "10 0 obj\n" +
            "<</Type/Page/MediaBox[0 0 595 842]/Resources<</Font<</F1 11 0 R/F2 12 0 R/F3 13 0 R/F4 14 0 R/F5 15 0 R/F6 16 0 R>>>>/Rotate 90/Contents 17 0 R/Annots 18 0 R/Parent 9 0 R>>\n" +
            "endobj\n" +
            "20 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Roman/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "21 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "22 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "23 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "24 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "25 0 obj\n" +
            "<</Length 1258/Filter/FlateDecode>>stream\n" +
            "x��V�r�8\u0010}�+�i+S�8�lc�<%�L�en����dkJ�\u001BЎ�\u0018I\u000E�\u0017�on�\u0006b�!�����R����H>0�2�!\u001AD�7Yv�un�\u001D\u001EAԏ`�v|\u0012����\u001D\u0003�`:�\\|�\u001E�v�f�]�x���h2�\u0012���MG80^\u001E\u0001���b�D���]�3�n�\u0007\f�\u007F\u001AOFWP\u0019�cNޏ�A��18\u001F�Z)��\u0011ʧ<E=�\"-�E\u0018\u0016ZˤȊ%\f\u0017\"��9���\u0017\n" +
            "Z��\"�<\u0015\u001AfJ�u\"R\\�\u0004�D�����K���~wkqk=�b�b���*�%�VX�rx�r��l5�Vl�\u0001lm��Nf�NP�vs9I\u0016JeW�q\u007F�2?�\"�G\u007FM\u007F�C�����J7P遚�(��\u001CQ�|\u000E\"OI�\\\u0015\u00165\u001D��'�=\u0005�9v�+��\u000B��)��=�\u001F4�j\u0002�O0Q3�\u0016\u001A�ν�\u000Fj!��\u0013�\u001F�����\u001F�x�s�x\f\u0018�Xp\\�\u001B�,0���\u0012�����\n" +
            "-�_A�]>T���iLϮ�'���<�<\b��\u001B�����̋}/�?����}�����c�ï$x��\b\u058Eւ�\u0007��\u0007����d+\bi�.!\u0015ޯ\u0004��Ti�8� �x�!�\n" +
            "\u0001�M'\b�\u0004~���W����݀��ЁO�G�Y\u001Ev \u001A\u001A\u0003��?֥������תS\u0011�\t%�\u0013\f*�}�ݱ�\u0001\u001E�\u0003\u0017' �-dH\u0011�\u0010w�ۆ[a\u0005L6��Ҕ�xԅOl[��%�����E��e�1��ܿ��`~�HP:�D�����FZ��[��\u0007�8�`��\u0006\u0012Uh���`�.�<�2�v�`�J&�\n" +
            "dn�J���iӝj\u0005�\n" +
            "��n\u0011W��\u0005��2e̯��Y��\"+E\u000F y\n" +
            "_q�}\u0014Y���T%���V<����%�\uEBBCn��s��o�o��X�u�&f\u0015Y�Y�ө4V�\u0007bδ\u0015�o��d�P�f~��]i��1.�X�ԅ�\u0004d\u0019Œ`D�����\u0015�\u0013�|�\u0005�)υF�\n" +
            "�\u000F\u001Aпcy�jd\u0017D��pՄ\u0015Q�\u001A�+��� �Ȯ@�t/�٧N��\u001DW0�IjuNs��\u0010I������\u0012�8�����Ѭ�m�+�����g����S��ԥ���\n" +
            "<���\u0001r94�H�Q\f�\n" +
            "G�\u0010�5*M�Z��*��v�y��\tU\u007F;\u0007�f�\t%+۸�=��\u0002H`.\u001F�:N�# �hVSx����V?��\u07B8�\u0014����\n" +
            "�\u0016{�4O\u0001Y�;\u000B̎\u0006�v\u0019��\u0012\u000F���Tt�<������d}�&\u000B���rq���d��h(>zE���L�������ҲA|\n" +
            "D�@d\n" +
            "�lp,����1\b����X|\u0012�b\u0012��\t=&�7��1���m�^�L|Qt\n" +
            "罖T[G|�Ʊ�\u0003��ʙt���\u007F��_0���#�p���������\u0007^�}*\u001E;\u0605굵�\u000Bk�e��bEMWv4�\u0012�ė:\u001B<�l���+?�5��\u0006\u0006�W�ARO��ȯ0�w�y����sX\u001AofP��a���\u0015��E#�\u001F��\u0014\n" +
            "endstream\n" +
            "endobj\n" +
            "19 0 obj\n" +
            "<</Type/Page/MediaBox[0 0 595 842]/Resources<</Font<</F1 20 0 R/F2 21 0 R/F3 22 0 R/F4 23 0 R/F5 24 0 R>>>>/Rotate 90/Contents 25 0 R/Annots 26 0 R/Parent 9 0 R>>\n" +
            "endobj\n" +
            "9 0 obj\n" +
            "<</Type/Pages/Count 3/Kids[1 0 R 10 0 R 19 0 R]>>\n" +
            "endobj\n" +
            "27 0 obj\n" +
            "<</Type/Catalog/Pages 9 0 R>>\n" +
            "endobj\n" +
            "28 0 obj\n" +
            "<</Producer(iText� 5.5.10 �2000-2015 iText Group NV \\(AGPL-version\\))/CreationDate(D:20191201204805-05'00')/ModDate(D:20191201204805-05'00')>>\n" +
            "endobj\n" +
            "xref\n" +
            "0 29\n" +
            "0000000000 65535 f \n" +
            "0000002096 00000 n \n" +
            "0000000015 00000 n \n" +
            "0000000105 00000 n \n" +
            "0000000193 00000 n \n" +
            "0000000282 00000 n \n" +
            "0000000375 00000 n \n" +
            "0000000475 00000 n \n" +
            "0000000000 65535 f \n" +
            "0000006333 00000 n \n" +
            "0000004173 00000 n \n" +
            "0000002267 00000 n \n" +
            "0000002358 00000 n \n" +
            "0000002447 00000 n \n" +
            "0000002537 00000 n \n" +
            "0000002631 00000 n \n" +
            "0000002732 00000 n \n" +
            "0000002829 00000 n \n" +
            "0000000000 65535 f \n" +
            "0000006154 00000 n \n" +
            "0000004362 00000 n \n" +
            "0000004453 00000 n \n" +
            "0000004542 00000 n \n" +
            "0000004632 00000 n \n" +
            "0000004726 00000 n \n" +
            "0000004827 00000 n \n" +
            "0000000000 65535 f \n" +
            "0000006398 00000 n \n" +
            "0000006444 00000 n \n" +
            "trailer\n" +
            "<</Size 29/Root 27 0 R/Info 28 0 R/ID [<24a5e6842c8587214c3ee605a81de9f9><24a5e6842c8587214c3ee605a81de9f9>]>>\n" +
            "%iText-5.5.10\n" +
            "startxref\n" +
            "6603\n" +
            "%%EOF\n" +
            "%PDF-1.4\n" +
            "%����\n" +
            "1 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "2 0 obj\n" +
            "<</Producer(iText� 5.5.10 �2000-2015 iText Group NV \\(AGPL-version\\); modified using iText� 5.5.10 �2000-2015 iText Group NV \\(AGPL-version\\))/CreationDate(D:20191201204805-05'00')/ModDate(D:20191201204805-05'00')>>\n" +
            "endobj\n" +
            "3 0 obj\n" +
            "<</Length 10/Filter/FlateDecode>>stream\n" +
            "x�+�\u0002  � |\n" +
            "endstream\n" +
            "endobj\n" +
            "4 0 obj\n" +
            "<</Length 90/Filter/FlateDecode>>stream\n" +
            "x�S\b�*�2P0T�5T0P0�4\u0005�ɹ@1�\u0010.�\bH���T��L�B!$�K?\"�P��@!$�� ,]�ΥQ����`����`�\u0019�\u0005�H�r\n" +
            "�\n" +
            "\u0004B �%\u0013�\n" +
            "endstream\n" +
            "endobj\n" +
            "5 0 obj\n" +
            "<</Length 10/Filter/FlateDecode>>stream\n" +
            "x�+�\u0002  � |\n" +
            "endstream\n" +
            "endobj\n" +
            "6 0 obj\n" +
            "<</Length 90/Filter/FlateDecode>>stream\n" +
            "x�S\b�*�2P0T�5T0P0�4\u0005�ɹ@1�\u0010.�\bH���T��L�B!$�K?\"�H��@!$�� ,]�ΥQ����`����`�\u0019�\u0005�H�r\n" +
            "�\n" +
            "\u0004B �f\u0013�\n" +
            "endstream\n" +
            "endobj\n" +
            "7 0 obj\n" +
            "<</Length 10/Filter/FlateDecode>>stream\n" +
            "x�+�\u0002  � |\n" +
            "endstream\n" +
            "endobj\n" +
            "8 0 obj\n" +
            "<</Length 88/Filter/FlateDecode>>stream\n" +
            "x�S\b�*�2P0T�5T0P0�4\u0005�ɹ@1�\u0010.�\bH���T��L�B!$�K?\"\u0013(d�\u0010��e �.J��(HLO\u0005��OS0�\f�\u0002J�s��p\u0005\u0002! ��\u0013�\n" +
            "endstream\n" +
            "endobj\n" +
            "9 0 obj\n" +
            "<</Type/Page/MediaBox[0 0 595 842]/Resources<</Font<</F1 10 0 R/F2 11 0 R/F3 12 0 R/F4 13 0 R/F5 14 0 R/Xi0 1 0 R>>>>/Rotate 90/Contents[7 0 R 15 0 R 8 0 R]/Parent 16 0 R>>\n" +
            "endobj\n" +
            "10 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Roman/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "11 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "12 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "13 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "14 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "15 0 obj\n" +
            "<</Length 1553/Filter/FlateDecode>>stream\n" +
            "x��X]S�8\u0014}ϯ�O;t\u0006�%\u007F�a�h\blw\u0017�B��Sv:�V\u0012����N(�h��^�Nbl�|\f� ��{ϹWҹ�] pD��`\u0014��8\u001B�\u001C��\u000Eh A\u0014�4\u0019��\u001D��s\u0002��t68�t:���b��������\u001By7��C(\u0010j��n���������8\u001E\u007F�r}3��\u001F�W\u0017�\u0013�|nao>N� �������R\u0016<��|�\u0013��%+Vr8�\u0013��4�+�D�JW\u0019�\u0017,��J�R'\u0018٢d)�\u0013�`&\u0015��,ᙈ�\u001F�T�\u000Bu�{�\u007FL�G��ڻ�\f����l���%+���R�������=�\u0006�}\u001B\u007F{�u�0����&^H��<���\u0011q� ph����&ą�\u0019�e� �\u001D�\u0019L��9W\"�\u0003��e�\\�\\�\u0010��?gv\u001B�����R\u0007�%EB\u001A:��\"�\u0006���F��{�x��\u000By�z�E�uI�ȏ�a;3o�x���.�\u0017\u0012�;\u0015Gp�\n" +
            "\u001D5N�\n" +
            "\u001Cn\u000F.�y��9,\u0010%�\u001F'\u001CvP\u07B3x�Sه����Qg�a��\u0014��}eK�v�~bs\u000EW�쎫Nb�ш:����!q��3|��x����t�\u0019uS�R�\u0013;.\u0006�\u0013���Q�w�\u001B�@\t(>��;|l�{Є�\u001D�u�6{�Ј�\"\u0018�(���\u0011�������g-�Z\u0001\\�\t\u001E��\u0018\\�x��\u0002�\u0019L��R'\u0002�n��4��T2�4�,�u�\br\t��I�h\u0004� Bz�yN5W̤\u001E�2���`��,h@\u001D:z�\u0004�)܀P��$\n" +
            "���z�\u0011m��Jˣn4MZ\u0019�u�>fuW�t��(5\u001F\u001Cx�_��9�\f�#7[�T�\u000BQ�6XaC��\u0019/�<oy����N�m�����\u0001�y�\u001B�`�W<\u0011eQm��A\u0015\u000F1����\u0002P��J\u0014H�\u0004���p|\u001BR3.�\u0002��\\��\u0017��d���X\u0013���W�\u000EߴLl��\u0016�����I�Ҷ��|@�(>\"��U��(NQtʂ+D<��=`p�!,Œ\u0017Hf&R����\u0002LG-`�FOw�0�JƼ( �ʋ�����fX\u001B��T?:�X�҇B\u0014fX�� r�]\"\u001F\u001Br�B�\u0017�\u0002̤(S�\u000B�\u0014Ƣ��\u000E\\��0�\n" +
            "�k�[�0��\u0011�Ĭ1���T�����\u0016�f|�\u0017l-d��%��L�\u0015�\u0003�JfV$(%>�L�<�_9p&3&r\u001Bn����4}�^A�����D�a�f�:\u000B��%W����jËRdU���p�\u0016n�&��d�=$ �#�Tz%�\u000E�A\u0014��\u0019��]�<�Iƺ\n" +
            "\u000B�YaC�[�H\u0019�9���t��Xz\u00022�\u001C��s�\t��r�\u00152?\u0018V\u0002룐�\u0011�@�4�B�6ٍ�\u0006�G����`uu|R�\f�49\u001A>e\u0012��RX�\u0012E\u000E.n�أ�����j�N��#X4v7`;}�Aϓ�~�ݹ�_��&�\u0017H�\u000E\b����ϋ|x\u001Bյ�k\u000B�c�o!�6؎̶�\u0019�Bw��m��h3���Z��*k\u0011gx\u0013嵢����:��\u05CA�Vؽ�\f��^+d[^{�\u0019^%��S�\u0016�J\u0012\u0003ǥ�y�\u0001�n\u052D�3�&\u0011,��{�A��]u}ٝ\u0016�\u0001��ϱ�8\uE341DԜ��.\u000B��?\b\u0012�ź�\u0005�\n" +
            "�V\u007F��,V[@\u0004�Mo@$${\u0003ڀ�\u0006H\u007Fpx����>�M�'8\u007F/.Ƅ�\n" +
            "7��kA��K�Jk|xc鏏���|T\u0002/�fC�����w\\�/nK�9��\u0017��\u0003s��U���k&��1կGԍ<ǫ?�t\t\u001EA�eb�\u0017�2M5G\u0014J�Ɲj\u0018�2�/$K�O�\u001D\u0005�i��\uEBF1D�B\u007F}�y'�Vf������B�Kj�8��w;},I\u001E'��\u0016y�VH^A\u000B%�o\teb��a8��|�i���z��{\n" +
            "endstream\n" +
            "endobj\n" +
            "16 0 obj\n" +
            "<</Type/Pages/Count 3/Kids[9 0 R 17 0 R 18 0 R]>>\n" +
            "endobj\n" +
            "17 0 obj\n" +
            "<</Type/Page/MediaBox[0 0 595 842]/Resources<</Font<</F1 19 0 R/F2 20 0 R/F3 21 0 R/F4 22 0 R/F5 23 0 R/F6 24 0 R/Xi1 1 0 R>>>>/Rotate 90/Contents[3 0 R 25 0 R 4 0 R]/Parent 16 0 R>>\n" +
            "endobj\n" +
            "19 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Roman/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "20 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "21 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "22 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "23 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "24 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Oblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "25 0 obj\n" +
            "<</Length 1275/Filter/FlateDecode>>stream\n" +
            "x��V�R�H\u0014��)�ՖViLw\u0012�x����*\n" +
            "x�5nM5�\u0011z�\u000F�I`�\u0017�G����\t ����\\@��t\u007F���c\u0002�S\u0006&8�C�A���u9lq\u0007\u001Cρa�2I��\u001Fg�\f\u0018��k������M�\u001B;�~��;\u0018~���?�\b\u0007��#��&!nB4�][+�N�?��U��;��=�C��&\u001E��\u000F���S�\u001EU:M3\fwX��\u0010�X��\u00109B�PJ\u0006ET�Й�d��\u0001���\n" +
            "�o�LD��B�k��\"\u0010!�2��Q�\u001Dznr����?]\".�m�e\n" +
            "��4�\u0018�\\�2M�>M��Y#��X�&Xb�x{=t-��|q6\b&i\u001A�o�������\u0018��c�e��F&\u0002:i���\u001E���M�2AT2\u0019�HB\u0012��\"GEG$&\u0001�H@7\u0014�©P�6xW)��mójJ�\t�\u0017\u0018���\\(�T�zP\n" +
            "Q�ĻJp��\f�\uE65F�x�c��Kh���\u001B�K\u0011L0���\u00104����%-_��Y�I�gcs�*�G1Fx(�\u0011�\u001D}۾�\n" +
            "�����e�k\u001A��)o�[��;l=Q\u000B�?\u000E_Hp�2\n" +
            "\u0007溣YN\u001B,�\n" +
            "����`)�i�,�+|)`�Y��G�/1\u001B\\���k\u0002��f�W]�j�I�`]�&ug�\u0004\n" +
            ".\f���Ì�\u001F��=�~ �7�ؼ�x��w{.i\u001E{y�&�\u001A\u001A��چ�\"�1�_�\n" +
            "uά\u0003V��\u0012Q���ˑmЫ�0�y�r\\�l��i���wH� ���6�U\u0011��\u0013�\u0012�(\u001B�mR�R��N6W0�\u001C��\u001C�\u000E\u001E)�#�a\u00043��&n^�2\tV�b���\u0004D�a<�\u0016\u0010Q�/tVW�E�\u0006�c��\u0005d�C2�\u0007�\u0017�Z�&:V�\u000Be�FVL$*���B�i��$f\u0006ܞ� Uc�ȿ˶~R�8�dc�|�RA(g�2�/N��&f��,\u001321H�\u0004+��)t\u001E�K�� C��HdP=)#����=�@��\u001Aȸ_#�N\u0016\u0019�D��\fx �T�U6�\u0001\n" +
            "��2��9\u0001S��IZ\u0010ϔbA�o\u0006\f��\\#\"���&b�F\\\u0007�\u0013�T\t\u0002Y�\u0010u�&HwY\u0011�U�?��s\u0018�EH���|B0b�T�\u0001R �e���|�w���\u0007bzu��\u001C&ސ��\u0019�M\u0528Oi�Mƪn�*\u0018cOq[~�ֺGn��'�[�,�W>��fȨ�h\u001Eۄ��:����H�#�Z��\u0015��+��w�L\u000E\u001AmΛ|�\u000F)�\u000F0\u007F\u001Fp�rOL\uF20E8�H�\bu��\"9\u0006F\u000B\u0010:uF4k��I����N��q��:��N`>Ŧ�,fP�\u058B�^`�^e����4S�1lY0Y�G��\u001E�\bs�}$jE\"7H\u000E\u001BG\u000F\u0014\u0019G��o��\u0003�9{y�&\u0011��\u0014��L�>NS�7�g;\u001F�g9�xzJ��A�\u000E�\tGw�\u0011%@\u001A�W�g�\u001F\u001Fe^�\u001877_���f��V��)7=˰��㮂�P�^+\\��(�:\u0016Ӑ|\u0013V\u001ASN�'�e�\u000F�e�\u0003��{KTOp����-�����m�trP-���\u000BK�\u0003\u0015�8�m\u0007\u001ET�lT�j�?\u007F��W\n" +
            "endstream\n" +
            "endobj\n" +
            "18 0 obj\n" +
            "<</Type/Page/MediaBox[0 0 595 842]/Resources<</Font<</F1 26 0 R/F2 27 0 R/F3 28 0 R/F4 29 0 R/F5 30 0 R/Xi2 1 0 R>>>>/Rotate 90/Contents[5 0 R 31 0 R 6 0 R]/Parent 16 0 R>>\n" +
            "endobj\n" +
            "26 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Roman/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "27 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "28 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Times-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "29 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-Bold/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "30 0 obj\n" +
            "<</Type/Font/Subtype/Type1/BaseFont/Helvetica-BoldOblique/Encoding/WinAnsiEncoding>>\n" +
            "endobj\n" +
            "31 0 obj\n" +
            "<</Length 1258/Filter/FlateDecode>>stream\n" +
            "x��V�r�8\u0010}�+�i+S�8�lc�<%�L�en����dkJ�\u001BЎ�\u0018I\u000E�\u0017�on�\u0006b�!�����R����H>0�2�!\u001AD�7Yv�un�\u001D\u001EAԏ`�v|\u0012����\u001D\u0003�`:�\\|�\u001E�v�f�]�x���h2�\u0012���MG80^\u001E\u0001���b�D���]�3�n�\u0007\f�\u007F\u001AOFWP\u0019�cNޏ�A��18\u001F�Z)��\u0011ʧ<E=�\"-�E\u0018\u0016ZˤȊ%\f\u0017\"��9���\u0017\n" +
            "Z��\"�<\u0015\u001AfJ�u\"R\\�\u0004�D�����K���~wkqk=�b�b���*�%�VX�rx�r��l5�Vl�\u0001lm��Nf�NP�vs9I\u0016JeW�q\u007F�2?�\"�G\u007FM\u007F�C�����J7P遚�(��\u001CQ�|\u000E\"OI�\\\u0015\u00165\u001D��'�=\u0005�9v�+��\u000B��)��=�\u001F4�j\u0002�O0Q3�\u0016\u001A�ν�\u000Fj!��\u0013�\u001F�����\u001F�x�s�x\f\u0018�Xp\\�\u001B�,0���\u0012�����\n" +
            "-�_A�]>T���iLϮ�'���<�<\b��\u001B�����̋}/�?����}�����c�ï$x��\b\u058Eւ�\u0007��\u0007����d+\bi�.!\u0015ޯ\u0004��Ti�8� �x�!�\n" +
            "\u0001�M'\b�\u0004~���W����݀��ЁO�G�Y\u001Ev \u001A\u001A\u0003��?֥������תS\u0011�\t%�\u0013\f*�}�ݱ�\u0001\u001E�\u0003\u0017' �-dH\u0011�\u0010w�ۆ[a\u0005L6��Ҕ�xԅOl[��%�����E��e�1��ܿ��`~�HP:�D�����FZ��[��\u0007�8�`��\u0006\u0012Uh���`�.�<�2�v�`�J&�\n" +
            "dn�J���iӝj\u0005�\n" +
            "��n\u0011W��\u0005��2e̯��Y��\"+E\u000F y\n" +
            "_q�}\u0014Y���T%���V<����%�\uEBBCn��s��o�o��X�u�&f\u0015Y�Y�ө4V�\u0007bδ\u0015�o��d�P�f~��]i��1.�X�ԅ�\u0004d\u0019Œ`D�����\u0015�\u0013�|�\u0005�)υF�\n" +
            "�\u000F\u001Aпcy�jd\u0017D��pՄ\u0015Q�\u001A�+��� �Ȯ@�t/�٧N��\u001DW0�IjuNs��\u0010I������\u0012�8�����Ѭ�m�+�����g����S��ԥ���\n" +
            "<���\u0001r94�H�Q\f�\n" +
            "G�\u0010�5*M�Z��*��v�y��\tU\u007F;\u0007�f�\t%+۸�=��\u0002H`.\u001F�:N�# �hVSx����V?��\u07B8�\u0014����\n" +
            "�\u0016{�4O\u0001Y�;\u000B̎\u0006�v\u0019��\u0012\u000F���Tt�<������d}�&\u000B���rq���d��h(>zE���L�������ҲA|\n" +
            "D�@d\n" +
            "�lp,����1\b����X|\u0012�b\u0012��\t=&�7��1���m�^�L|Qt\n" +
            "罖T[G|�Ʊ�\u0003��ʙt���\u007F��_0���#�p���������\u0007^�}*\u001E;\u0605굵�\u000Bk�e��bEMWv4�\u0012�ė:\u001B<�l���+?�5��\u0006\u0006�W�ARO��ȯ0�w�y����sX\u001AofP��a���\u0015��E#�\u001F��\u0014\n" +
            "endstream\n" +
            "endobj\n" +
            "32 0 obj\n" +
            "<</Type/Catalog/Pages 16 0 R>>\n" +
            "endobj\n" +
            "xref\n" +
            "0 33\n" +
            "0000000000 65535 f \n" +
            "0000000015 00000 n \n" +
            "0000000103 00000 n \n" +
            "0000000334 00000 n \n" +
            "0000000410 00000 n \n" +
            "0000000566 00000 n \n" +
            "0000000642 00000 n \n" +
            "0000000798 00000 n \n" +
            "0000000874 00000 n \n" +
            "0000001028 00000 n \n" +
            "0000001216 00000 n \n" +
            "0000001307 00000 n \n" +
            "0000001396 00000 n \n" +
            "0000001486 00000 n \n" +
            "0000001580 00000 n \n" +
            "0000001681 00000 n \n" +
            "0000003303 00000 n \n" +
            "0000003369 00000 n \n" +
            "0000005474 00000 n \n" +
            "0000003568 00000 n \n" +
            "0000003659 00000 n \n" +
            "0000003748 00000 n \n" +
            "0000003838 00000 n \n" +
            "0000003932 00000 n \n" +
            "0000004033 00000 n \n" +
            "0000004130 00000 n \n" +
            "0000005663 00000 n \n" +
            "0000005754 00000 n \n" +
            "0000005843 00000 n \n" +
            "0000005933 00000 n \n" +
            "0000006027 00000 n \n" +
            "0000006128 00000 n \n" +
            "0000007455 00000 n \n" +
            "trailer\n" +
            "<</Size 33/Root 32 0 R/Info 2 0 R/ID [<24a5e6842c8587214c3ee605a81de9f9><5bb2afa1ad69fac62f53d4164495492a>]>>\n" +
            "%iText-5.5.10\n" +
            "startxref\n" +
            "7502\n" +
            "%%EOF\n";
}