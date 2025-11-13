/*
 * This file is part of Objectos UI.
 * Copyright (C) 2025 Objectos Software LTDA.
 *
 * Objectos UI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Objectos UI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Objectos UI.  If not, see <https://www.gnu.org/licenses/>.
 */
package objectos.ui;

import objectos.way.Css;

final class CarbonPlex implements Css.Library {

  @Override
  public final void configure(Css.Library.Options opts) {
    opts.theme("""
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 100;
      src: local("IBM Plex Sans Thin"), local("IBMPlexSans-Thin"), url("IBMPlexSans-Thin-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 100;
      src: local("IBM Plex Sans Thin"), local("IBMPlexSans-Thin"), url("IBMPlexSans-Thin-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 100;
      src: local("IBM Plex Sans Thin"), local("IBMPlexSans-Thin"), url("IBMPlexSans-Thin-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 100;
      src: local("IBM Plex Sans Thin"), local("IBMPlexSans-Thin"), url("IBMPlexSans-Thin-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 100;
      src: local("IBM Plex Sans Thin"), local("IBMPlexSans-Thin"), url("IBMPlexSans-Thin-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 100;
      src: local("IBM Plex Sans Thin"), local("IBMPlexSans-Thin"), url("IBMPlexSans-Thin-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 100;
      src: local("IBM Plex Sans Thin Italic"), local("IBMPlexSans-ThinItalic"), url("IBMPlexSans-ThinItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 100;
      src: local("IBM Plex Sans Thin Italic"), local("IBMPlexSans-ThinItalic"), url("IBMPlexSans-ThinItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 100;
      src: local("IBM Plex Sans Thin Italic"), local("IBMPlexSans-ThinItalic"), url("IBMPlexSans-ThinItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 100;
      src: local("IBM Plex Sans Thin Italic"), local("IBMPlexSans-ThinItalic"), url("IBMPlexSans-ThinItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 100;
      src: local("IBM Plex Sans Thin Italic"), local("IBMPlexSans-ThinItalic"), url("IBMPlexSans-ThinItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 100;
      src: local("IBM Plex Sans Thin Italic"), local("IBMPlexSans-ThinItalic"), url("IBMPlexSans-ThinItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt Italic"), local("IBMPlexSans-ExtLtItalic"), url("IBMPlexSans-ExtraLightItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt Italic"), local("IBMPlexSans-ExtLtItalic"), url("IBMPlexSans-ExtraLightItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt Italic"), local("IBMPlexSans-ExtLtItalic"), url("IBMPlexSans-ExtraLightItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt Italic"), local("IBMPlexSans-ExtLtItalic"), url("IBMPlexSans-ExtraLightItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt Italic"), local("IBMPlexSans-ExtLtItalic"), url("IBMPlexSans-ExtraLightItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt Italic"), local("IBMPlexSans-ExtLtItalic"), url("IBMPlexSans-ExtraLightItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt"), local("IBMPlexSans-ExtLt"), url("IBMPlexSans-ExtraLight-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt"), local("IBMPlexSans-ExtLt"), url("IBMPlexSans-ExtraLight-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt"), local("IBMPlexSans-ExtLt"), url("IBMPlexSans-ExtraLight-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt"), local("IBMPlexSans-ExtLt"), url("IBMPlexSans-ExtraLight-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt"), local("IBMPlexSans-ExtLt"), url("IBMPlexSans-ExtraLight-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 200;
      src: local("IBM Plex Sans ExtLt"), local("IBMPlexSans-ExtLt"), url("IBMPlexSans-ExtraLight-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 300;
      src: local("IBM Plex Sans Light"), local("IBMPlexSans-Light"), url("IBMPlexSans-Light-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 300;
      src: local("IBM Plex Sans Light"), local("IBMPlexSans-Light"), url("IBMPlexSans-Light-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 300;
      src: local("IBM Plex Sans Light"), local("IBMPlexSans-Light"), url("IBMPlexSans-Light-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 300;
      src: local("IBM Plex Sans Light"), local("IBMPlexSans-Light"), url("IBMPlexSans-Light-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 300;
      src: local("IBM Plex Sans Light"), local("IBMPlexSans-Light"), url("IBMPlexSans-Light-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 300;
      src: local("IBM Plex Sans Light"), local("IBMPlexSans-Light"), url("IBMPlexSans-Light-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 300;
      src: local("IBM Plex Sans Light Italic"), local("IBMPlexSans-LightItalic"), url("IBMPlexSans-LightItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 300;
      src: local("IBM Plex Sans Light Italic"), local("IBMPlexSans-LightItalic"), url("IBMPlexSans-LightItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 300;
      src: local("IBM Plex Sans Light Italic"), local("IBMPlexSans-LightItalic"), url("IBMPlexSans-LightItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 300;
      src: local("IBM Plex Sans Light Italic"), local("IBMPlexSans-LightItalic"), url("IBMPlexSans-LightItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 300;
      src: local("IBM Plex Sans Light Italic"), local("IBMPlexSans-LightItalic"), url("IBMPlexSans-LightItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 300;
      src: local("IBM Plex Sans Light Italic"), local("IBMPlexSans-LightItalic"), url("IBMPlexSans-LightItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 400;
      src: local("IBM Plex Sans Italic"), local("IBMPlexSans-Italic"), url("IBMPlexSans-Italic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 400;
      src: local("IBM Plex Sans Italic"), local("IBMPlexSans-Italic"), url("IBMPlexSans-Italic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 400;
      src: local("IBM Plex Sans Italic"), local("IBMPlexSans-Italic"), url("IBMPlexSans-Italic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 400;
      src: local("IBM Plex Sans Italic"), local("IBMPlexSans-Italic"), url("IBMPlexSans-Italic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 400;
      src: local("IBM Plex Sans Italic"), local("IBMPlexSans-Italic"), url("IBMPlexSans-Italic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 400;
      src: local("IBM Plex Sans Italic"), local("IBMPlexSans-Italic"), url("IBMPlexSans-Italic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 400;
      src: local("IBM Plex Sans"), local("IBMPlexSans"), url("IBMPlexSans-Regular-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 400;
      src: local("IBM Plex Sans"), local("IBMPlexSans"), url("IBMPlexSans-Regular-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 400;
      src: local("IBM Plex Sans"), local("IBMPlexSans"), url("IBMPlexSans-Regular-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 400;
      src: local("IBM Plex Sans"), local("IBMPlexSans"), url("IBMPlexSans-Regular-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 400;
      src: local("IBM Plex Sans"), local("IBMPlexSans"), url("IBMPlexSans-Regular-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 400;
      src: local("IBM Plex Sans"), local("IBMPlexSans"), url("IBMPlexSans-Regular-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 450;
      src: local("IBM Plex Sans Text"), local("IBMPlexSans-Text"), url("IBMPlexSans-Text-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 450;
      src: local("IBM Plex Sans Text"), local("IBMPlexSans-Text"), url("IBMPlexSans-Text-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 450;
      src: local("IBM Plex Sans Text"), local("IBMPlexSans-Text"), url("IBMPlexSans-Text-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 450;
      src: local("IBM Plex Sans Text"), local("IBMPlexSans-Text"), url("IBMPlexSans-Text-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 450;
      src: local("IBM Plex Sans Text"), local("IBMPlexSans-Text"), url("IBMPlexSans-Text-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 450;
      src: local("IBM Plex Sans Text"), local("IBMPlexSans-Text"), url("IBMPlexSans-Text-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 450;
      src: local("IBM Plex Sans Text Italic"), local("IBMPlexSans-TextItalic"), url("IBMPlexSans-TextItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 450;
      src: local("IBM Plex Sans Text Italic"), local("IBMPlexSans-TextItalic"), url("IBMPlexSans-TextItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 450;
      src: local("IBM Plex Sans Text Italic"), local("IBMPlexSans-TextItalic"), url("IBMPlexSans-TextItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 450;
      src: local("IBM Plex Sans Text Italic"), local("IBMPlexSans-TextItalic"), url("IBMPlexSans-TextItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 450;
      src: local("IBM Plex Sans Text Italic"), local("IBMPlexSans-TextItalic"), url("IBMPlexSans-TextItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 450;
      src: local("IBM Plex Sans Text Italic"), local("IBMPlexSans-TextItalic"), url("IBMPlexSans-TextItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 500;
      src: local("IBM Plex Sans Medm"), local("IBMPlexSans-Medm"), url("IBMPlexSans-Medium-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 500;
      src: local("IBM Plex Sans Medm"), local("IBMPlexSans-Medm"), url("IBMPlexSans-Medium-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 500;
      src: local("IBM Plex Sans Medm"), local("IBMPlexSans-Medm"), url("IBMPlexSans-Medium-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 500;
      src: local("IBM Plex Sans Medm"), local("IBMPlexSans-Medm"), url("IBMPlexSans-Medium-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 500;
      src: local("IBM Plex Sans Medm"), local("IBMPlexSans-Medm"), url("IBMPlexSans-Medium-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 500;
      src: local("IBM Plex Sans Medm"), local("IBMPlexSans-Medm"), url("IBMPlexSans-Medium-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 500;
      src: local("IBM Plex Sans Medm Italic"), local("IBMPlexSans-MedmItalic"), url("IBMPlexSans-MediumItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 500;
      src: local("IBM Plex Sans Medm Italic"), local("IBMPlexSans-MedmItalic"), url("IBMPlexSans-MediumItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 500;
      src: local("IBM Plex Sans Medm Italic"), local("IBMPlexSans-MedmItalic"), url("IBMPlexSans-MediumItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 500;
      src: local("IBM Plex Sans Medm Italic"), local("IBMPlexSans-MedmItalic"), url("IBMPlexSans-MediumItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 500;
      src: local("IBM Plex Sans Medm Italic"), local("IBMPlexSans-MedmItalic"), url("IBMPlexSans-MediumItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 500;
      src: local("IBM Plex Sans Medm Italic"), local("IBMPlexSans-MedmItalic"), url("IBMPlexSans-MediumItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld Italic"), local("IBMPlexSans-SmBldItalic"), url("IBMPlexSans-SemiBoldItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld Italic"), local("IBMPlexSans-SmBldItalic"), url("IBMPlexSans-SemiBoldItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld Italic"), local("IBMPlexSans-SmBldItalic"), url("IBMPlexSans-SemiBoldItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld Italic"), local("IBMPlexSans-SmBldItalic"), url("IBMPlexSans-SemiBoldItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld Italic"), local("IBMPlexSans-SmBldItalic"), url("IBMPlexSans-SemiBoldItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld Italic"), local("IBMPlexSans-SmBldItalic"), url("IBMPlexSans-SemiBoldItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld"), local("IBMPlexSans-SmBld"), url("IBMPlexSans-SemiBold-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld"), local("IBMPlexSans-SmBld"), url("IBMPlexSans-SemiBold-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld"), local("IBMPlexSans-SmBld"), url("IBMPlexSans-SemiBold-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld"), local("IBMPlexSans-SmBld"), url("IBMPlexSans-SemiBold-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld"), local("IBMPlexSans-SmBld"), url("IBMPlexSans-SemiBold-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 600;
      src: local("IBM Plex Sans SmBld"), local("IBMPlexSans-SmBld"), url("IBMPlexSans-SemiBold-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 700;
      src: local("IBM Plex Sans Bold"), local("IBMPlexSans-Bold"), url("IBMPlexSans-Bold-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 700;
      src: local("IBM Plex Sans Bold"), local("IBMPlexSans-Bold"), url("IBMPlexSans-Bold-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 700;
      src: local("IBM Plex Sans Bold"), local("IBMPlexSans-Bold"), url("IBMPlexSans-Bold-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 700;
      src: local("IBM Plex Sans Bold"), local("IBMPlexSans-Bold"), url("IBMPlexSans-Bold-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 700;
      src: local("IBM Plex Sans Bold"), local("IBMPlexSans-Bold"), url("IBMPlexSans-Bold-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: normal;
      font-weight: 700;
      src: local("IBM Plex Sans Bold"), local("IBMPlexSans-Bold"), url("IBMPlexSans-Bold-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 700;
      src: local("IBM Plex Sans Bold Italic"), local("IBMPlexSans-BoldItalic"), url("IBMPlexSans-BoldItalic-Cyrillic.woff2") format("woff2");
      unicode-range: U+0400-045F, U+0462-0463, U+046A-046B, U+0472-0475, U+0490-04C2, U+04CF-04D9, U+04DC-04E9, U+04EE-04F9, U+0524-0525
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 700;
      src: local("IBM Plex Sans Bold Italic"), local("IBMPlexSans-BoldItalic"), url("IBMPlexSans-BoldItalic-Greek.woff2") format("woff2");
      unicode-range: U+037E, U+0386-038A, U+038C, U+038E-03A1, U+03A3-03CE
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 700;
      src: local("IBM Plex Sans Bold Italic"), local("IBMPlexSans-BoldItalic"), url("IBMPlexSans-BoldItalic-Latin1.woff2") format("woff2");
      unicode-range: U+0000, U+000D, U+0020-007E, U+00A0-00FF, U+0131, U+0152-0153, U+02C6, U+02DA, U+02DC, U+2013-2014, U+2018-201A, U+201C-201E, U+2020-2022, U+2026, U+2030, U+2039-203A, U+2044, U+20AC, U+2122, U+2212, U+FB01-FB02
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 700;
      src: local("IBM Plex Sans Bold Italic"), local("IBMPlexSans-BoldItalic"), url("IBMPlexSans-BoldItalic-Latin2.woff2") format("woff2");
      unicode-range: U+0100-0101, U+0104-0130, U+0132-0151, U+0154-017F, U+018F, U+0192, U+01A0-01A1, U+01AF-01B0, U+01FA-01FF, U+0218-021B, U+0237, U+0259, U+1E80-1E85, U+1E9E, U+20A1, U+20A4, U+20A6, U+20A8-20AA, U+20AD-20AE, U+20B1-20B2, U+20B4-20B5, U+20B8-20BA, U+20BD, U+20BF
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 700;
      src: local("IBM Plex Sans Bold Italic"), local("IBMPlexSans-BoldItalic"), url("IBMPlexSans-BoldItalic-Latin3.woff2") format("woff2");
      unicode-range: U+0102-0103, U+01CD-01DC, U+1EA0-1EF9, U+20AB
    }
    @font-face {
      font-family: 'IBM Plex Sans';
      font-style: italic;
      font-weight: 700;
      src: local("IBM Plex Sans Bold Italic"), local("IBMPlexSans-BoldItalic"), url("IBMPlexSans-BoldItalic-Pi.woff2") format("woff2");
      unicode-range: U+0E3F, U+2000-200D, U+2015, U+2028-2029, U+202F, U+2032-2033, U+2070, U+2074-2079, U+2080-2089, U+2113, U+2116, U+2126, U+212E, U+2150-2151, U+2153-215E, U+2190-2199, U+21A9-21AA, U+21B0-21B3, U+21B6-21B7, U+21BA-21BB, U+21C4, U+21C6, U+2202, U+2206, U+220F, U+2211, U+2215, U+221A, U+221E, U+222B, U+2248, U+2260, U+2264-2265, U+25CA, U+2713, U+274C, U+2B0E-2B11, U+ECE0, U+EFCC, U+FEFF, U+FFFD
    }
    """);
  }

}
