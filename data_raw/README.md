V tej podmapi se nahajajo podatki, na osnovi katerih smo/bomo učili klasifikatorje.

*  V mapi *_promise_* so pridobljeni podatki iz repozitorija **tera-PROMISE** (http://openscience.us/repo/defect/ck/)
*  V mapi *_bug-inf-usi_* so pridobljeni podatki iz **Bug prediction dataset** (http://bug.inf.usi.ch/download.php)

#ANALIZA PODATKOV

-------------------------------------------------------------------------------------------------------------------------

Podatki so pridobljeni iz powershell skript _analyzer.ps1_ in _attr\_analyzer_.

##teraPROMISE repozitorij

| Datoteka | Število atributov | Število instanc |
|----------|-------------------|-----------------|
| ant/ant-1.3.csv | 24 | 187 |
| ant/ant-1.4.csv | 24 | 265 |
| ant/ant-1.5.csv | 24 | 401 |
| ant/ant-1.6.csv | 24 | 523 |
| ant/ant-1.7.csv | 24 | 745 |
| arc/arc.csv | 24 | 234 |
| berek/berek.csv | 24 | 43 |
| camel/camel-1.0.csv | 24 | 339 |
| camel/camel-1.2.csv | 24 | 608 |
| camel/camel-1.4.csv | 24 | 872 |
| camel/camel-1.6.csv | 24 | 965 |
| ckjm/ckjm.csv | 24 | 10 |
| e-learning/e-learning.csv | 24 | 64 |
| forrest/forrest-0.6.csv | 24 | 6 |
| forrest/forrest-0.7.csv | 24 | 29 |
| forrest/forrest-0.8.csv | 24 | 32 |
| intercafe/intercafe.csv | 24 | 27 |
| ivy/ivy-1.1.csv | 24 | 111 |
| ivy/ivy-1.4.csv | 24 | 241 |
| ivy/ivy-2.0.csv | 24 | 352 |
| jedit/jedit-3.2.csv | 24 | 272 |
| jedit/jedit-4.0.csv | 24 | 306 |
| jedit/jedit-4.1.csv | 24 | 312 |
| jedit/jedit-4.2.csv | 24 | 367 |
| jedit/jedit-4.3.csv | 24 | 492 |
| kalkulator/kalkulator.csv | 24 | 27 |
| log4j/log4j-1.0.csv | 24 | 135 |
| log4j/log4j-1.1.csv | 24 | 109 |
| log4j/log4j-1.2.csv | 24 | 205 |
| lucene/lucene-2.0.csv | 24 | 195 |
| lucene/lucene-2.2.csv | 24 | 247 |
| lucene/lucene-2.4.csv | 24 | 340 |
| nieruchomosci/nieruchomosci.csv | 24 | 27 |
| pdftranslator/pdftranslator.csv | 24 | 33 |
| poi/poi-1.5.csv | 24 | 237 |
| poi/poi-2.0.csv | 24 | 314 |
| poi/poi-2.5.csv | 24 | 385 |
| poi/poi-3.0.csv | 24 | 442 |
| prop/prop-1.csv | 23 | 18471 |
| prop/prop-2.csv | 23 | 23014 |
| prop/prop-3.csv | 23 | 10274 |
| prop/prop-4.csv | 23 | 8718 |
| prop/prop-5.csv | 23 | 8516 |
| prop/prop-6.csv | 23 | 660 |
| redaktor/redaktor.csv | 24 | 176 |
| serapion/serapion.csv | 24 | 45 |
| skarbonka/skarbonka.csv | 24 | 45 |
| sklelbagd/sklebagd.csv | 24 | 20 |
| synapse/synapse-1.0.csv | 24 | 157 |
| synapse/synapse-1.1.csv | 24 | 222 |
| synapse/synapse-1.2.csv | 24 | 256 |
| systemdata/systemdata.csv | 24 | 65 |
| szybkafucha/szybkafucha.csv | 24 | 25 |
| termoproject/termoproject.csv | 24 | 42 |
| tomcat/tomcat.csv | 24 | 858 |
| velocity/velocity-1.4.csv | 24 | 196 |
| velocity/velocity-1.5.csv | 24 | 214 |
| velocity/velocity-1.6.csv | 24 | 229 |
| workflow/workflow.csv | 24 | 39 |
| wspomaganiepi/wspomaganiepi.csv | 24 | 18 |
| xalan/xalan-2.4.csv | 24 | 723 |
| xalan/xalan-2.5.csv | 24 | 803 |
| xalan/xalan-2.6.csv | 24 | 885 |
| xalan/xalan-2.7.csv | 24 | 909 |
| xerces/xerces-1.2.csv | 24 | 440 |
| xerces/xerces-1.3.csv | 24 | 453 |
| xerces/xerces-1.4.csv | 24 | 588 |
| xerces/xerces-init.csv | 24 | 162 |
| zuzel/zuzel.csv | 24 | 29 |

Skupaj instanc: **87751**

Minimalno st. atributov: **23**

Povprecno st. atributov: **23.9130434782609**

Maksimalno st. atributov: **24**

------------------------------
###Analiza atributov

| Datoteka | Atributi |
|----------|----------|
| ant/ant-1.3.csv | Project;Version;Class;wmc;dit;noc;cbo;rfc;lcom;ca;ce;npm;lcom3;loc;dam;moa;mfa;cam;ic;cbm;amc;max_cc;avg_cc;bug |
| ant/ant-1.4.csv | Project;Version;Class;wmc;dit;noc;cbo;rfc;lcom;ca;ce;npm;lcom3;loc;dam;moa;mfa;cam;ic;cbm;amc;max_cc;avg_cc;bug |
| ant/ant-1.5.csv | Project;Version;Class;wmc;dit;noc;cbo;rfc;lcom;ca;ce;npm;lcom3;loc;dam;moa;mfa;cam;ic;cbm;amc;max_cc;avg_cc;bug |
| ant/ant-1.6.csv | Project;Version;Class;wmc;dit;noc;cbo;rfc;lcom;ca;ce;npm;lcom3;loc;dam;moa;mfa;cam;ic;cbm;amc;max_cc;avg_cc;bug |
| ant/ant-1.7.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| arc/arc.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| berek/berek.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| camel/camel-1.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| camel/camel-1.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| camel/camel-1.4.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| camel/camel-1.6.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| ckjm/ckjm.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| e-learning/e-learning.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| forrest/forrest-0.6.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| forrest/forrest-0.7.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| forrest/forrest-0.8.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| intercafe/intercafe.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| ivy/ivy-1.1.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| ivy/ivy-1.4.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| ivy/ivy-2.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| jedit/jedit-3.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| jedit/jedit-4.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| jedit/jedit-4.1.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| jedit/jedit-4.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| jedit/jedit-4.3.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| kalkulator/kalkulator.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| log4j/log4j-1.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| log4j/log4j-1.1.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| log4j/log4j-1.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| lucene/lucene-2.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| lucene/lucene-2.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| lucene/lucene-2.4.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| nieruchomosci/nieruchomosci.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| pdftranslator/pdftranslator.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| poi/poi-1.5.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| poi/poi-2.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| poi/poi-2.5.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| poi/poi-3.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| prop/prop-1.csv | Name,version,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| prop/prop-2.csv | Name,version,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| prop/prop-3.csv | Name,version,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| prop/prop-4.csv | Name,version,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| prop/prop-5.csv | Name,version,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| prop/prop-6.csv | Name,version,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| redaktor/redaktor.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| serapion/serapion.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| skarbonka/skarbonka.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| sklelbagd/sklebagd.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| synapse/synapse-1.0.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| synapse/synapse-1.1.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| synapse/synapse-1.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| systemdata/systemdata.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| szybkafucha/szybkafucha.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| termoproject/termoproject.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| tomcat/tomcat.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| velocity/velocity-1.4.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| velocity/velocity-1.5.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| velocity/velocity-1.6.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| workflow/workflow.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| wspomaganiepi/wspomaganiepi.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xalan/xalan-2.4.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xalan/xalan-2.5.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xalan/xalan-2.6.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xalan/xalan-2.7.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xerces/xerces-1.2.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xerces/xerces-1.3.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xerces/xerces-1.4.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| xerces/xerces-init.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |
| zuzel/zuzel.csv | name,version,name,wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug |

Skupnih atributov: 21

Skupni atributi: wmc, dit, noc, cbo, rfc, lcom, ca, ce, npm, lcom3, loc, dam, moa, mfa, cam, ic, cbm, amc, max_cc, avg_cc, bug

------------------------------

##Bug prediction dataset repozitorij

###CK metrike

| Datoteka | Število atributov | Število instanc |
|----------|-------------------|-----------------|
| eclipse-jdt/single-version-ck-oo.csv | 24 | 997 |
| eclipse-pde/single-version-ck-oo.csv | 24 | 1497 |
| equinox-framework/single-version-ck-oo.csv | 24 | 324 |
| lucene/single-version-ck-oo.csv | 24 | 691 |
| mylyn/single-version-ck-oo.csv | 24 | 1862 |

Skupaj instanc: **5371**

Minimalno st. atributov: **24**

Povprecno st. atributov: **24**

Maksimalno st. atributov: **24**

###Metrike sprememb

| Datoteka | Število atributov | Število instanc |
|----------|-------------------|-----------------|
| eclipse-jdt/change-metrics.csv | 22 | 997 |
| eclipse-pde/change-metrics.csv | 22 | 1497 |
| equinox-framework/change-metrics.csv | 22 | 324 |
| lucene/change-metrics.csv | 22 | 691 |
| mylyn/change-metrics.csv | 22 | 1862 |

Skupaj instanc: **5371**

Minimalno st. atributov: **22**

Povprecno st. atributov: **22**

Maksimalno st. atributov: **22**

------------------------------
###Analiza atributov

#### CK Metrike

| Datoteka | Atributi |
|----------|----------|
| eclipse-jdt/single-version-ck-oo.csv | classname ; cbo ; dit ; fanIn ; fanOut ; lcom ; noc ; numberOfAttributes ; numberOfAttributesInherited ; numberOfLinesOfCode ; numberOfMethods ; numberOfMethodsInherited ; numberOfPrivateAttributes ; numberOfPrivateMethods ; numberOfPublicAttributes ; numberOfPublicMethods ; rfc ; wmc ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| eclipse-pde/single-version-ck-oo.csv | classname ; cbo ; dit ; fanIn ; fanOut ; lcom ; noc ; numberOfAttributes ; numberOfAttributesInherited ; numberOfLinesOfCode ; numberOfMethods ; numberOfMethodsInherited ; numberOfPrivateAttributes ; numberOfPrivateMethods ; numberOfPublicAttributes ; numberOfPublicMethods ; rfc ; wmc ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| equinox-framework/single-version-ck-oo.csv | classname ; cbo ; dit ; fanIn ; fanOut ; lcom ; noc ; numberOfAttributes ; numberOfAttributesInherited ; numberOfLinesOfCode ; numberOfMethods ; numberOfMethodsInherited ; numberOfPrivateAttributes ; numberOfPrivateMethods ; numberOfPublicAttributes ; numberOfPublicMethods ; rfc ; wmc ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| lucene/single-version-ck-oo.csv | classname ; cbo ; dit ; fanIn ; fanOut ; lcom ; noc ; numberOfAttributes ; numberOfAttributesInherited ; numberOfLinesOfCode ; numberOfMethods ; numberOfMethodsInherited ; numberOfPrivateAttributes ; numberOfPrivateMethods ; numberOfPublicAttributes ; numberOfPublicMethods ; rfc ; wmc ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| mylyn/single-version-ck-oo.csv | classname ; cbo ; dit ; fanIn ; fanOut ; lcom ; noc ; numberOfAttributes ; numberOfAttributesInherited ; numberOfLinesOfCode ; numberOfMethods ; numberOfMethodsInherited ; numberOfPrivateAttributes ; numberOfPrivateMethods ; numberOfPublicAttributes ; numberOfPublicMethods ; rfc ; wmc ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |

Skupnih atributov: 24

Skupni atributi: classname ,  cbo ,  dit ,  fanIn ,  fanOut ,  lcom ,  noc ,  numberOfAttributes ,  numberOfAttributesInherited ,  numberOfLinesOfCode ,  numberOfMethods ,  numberOfMethodsInherited ,  numberOfPrivateAttributes ,  numberOfPrivateMethods ,  numberOfPublicAttributes ,  numberOfPublicMethods ,  rfc ,  wmc ,  bugs ,  nonTrivialBugs ,  majorBugs ,  criticalBugs ,  highPriorityBugs

#### Metrike sprememb

| Datoteka | Atributi |
|----------|----------|
| eclipse-jdt/change-metrics.csv | classname ; numberOfVersionsUntil: ; numberOfFixesUntil: ; numberOfRefactoringsUntil: ; numberOfAuthorsUntil: ; linesAddedUntil: ; maxLinesAddedUntil: ; avgLinesAddedUntil: ; linesRemovedUntil: ; maxLinesRemovedUntil: ; avgLinesRemovedUntil: ; codeChurnUntil: ; maxCodeChurnUntil: ; avgCodeChurnUntil: ; ageWithRespectTo: ; weightedAgeWithRespectTo: ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| eclipse-pde/change-metrics.csv | classname ; numberOfVersionsUntil: ; numberOfFixesUntil: ; numberOfRefactoringsUntil: ; numberOfAuthorsUntil: ; linesAddedUntil: ; maxLinesAddedUntil: ; avgLinesAddedUntil: ; linesRemovedUntil: ; maxLinesRemovedUntil: ; avgLinesRemovedUntil: ; codeChurnUntil: ; maxCodeChurnUntil: ; avgCodeChurnUntil: ; ageWithRespectTo: ; weightedAgeWithRespectTo: ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| equinox-framework/change-metrics.csv | classname ; numberOfVersionsUntil: ; numberOfFixesUntil: ; numberOfRefactoringsUntil: ; numberOfAuthorsUntil: ; linesAddedUntil: ; maxLinesAddedUntil: ; avgLinesAddedUntil: ; linesRemovedUntil: ; maxLinesRemovedUntil: ; avgLinesRemovedUntil: ; codeChurnUntil: ; maxCodeChurnUntil: ; avgCodeChurnUntil: ; ageWithRespectTo: ; weightedAgeWithRespectTo: ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| lucene/change-metrics.csv | classname ; numberOfVersionsUntil: ; numberOfFixesUntil: ; numberOfRefactoringsUntil: ; numberOfAuthorsUntil: ; linesAddedUntil: ; maxLinesAddedUntil: ; avgLinesAddedUntil: ; linesRemovedUntil: ; maxLinesRemovedUntil: ; avgLinesRemovedUntil: ; codeChurnUntil: ; maxCodeChurnUntil: ; avgCodeChurnUntil: ; ageWithRespectTo: ; weightedAgeWithRespectTo: ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |
| mylyn/change-metrics.csv | classname ; numberOfVersionsUntil: ; numberOfFixesUntil: ; numberOfRefactoringsUntil: ; numberOfAuthorsUntil: ; linesAddedUntil: ; maxLinesAddedUntil: ; avgLinesAddedUntil: ; linesRemovedUntil: ; maxLinesRemovedUntil: ; avgLinesRemovedUntil: ; codeChurnUntil: ; maxCodeChurnUntil: ; avgCodeChurnUntil: ; ageWithRespectTo: ; weightedAgeWithRespectTo: ; bugs ; nonTrivialBugs ; majorBugs ; criticalBugs ; highPriorityBugs ;  |

Skupnih atributov: 22

Skupni atributi: classname ,  numberOfVersionsUntil: ,  numberOfFixesUntil: ,  numberOfRefactoringsUntil: ,  numberOfAuthorsUntil: ,  linesAddedUntil: ,  maxLinesAddedUntil: ,  avgLinesAddedUntil: ,  linesRemovedUntil: ,  maxLinesRemovedUntil: ,  avgLinesRemovedUntil: ,  codeChurnUntil: ,  maxCodeChurnUntil: ,  avgCodeChurnUntil: ,  ageWithRespectTo: ,  weightedAgeWithRespectTo: ,  bugs ,  nonTrivialBugs ,  majorBugs ,  criticalBugs ,  highPriorityBugs

## Skupni podatki - objektne metrike (povzetek)

**_Opombe:_** *število atributov je enako številu enako poimenovanih stolpcev v .csv datoteki. V stolpcu repozitoriju skupni atributi pa se nahajajo imena teh atributov*

| Repozitorij | Število atributov |Št. instanc| Repozitoriju skupni atributi |
|----------|----------|------------|----------|
| tera-PROMISE | 21 | 87.751 | wmc, dit, noc, cbo, rfc, lcom, ca, ce, npm, lcom3, loc, dam, moa, mfa, cam, ic, cbm, amc, max_cc, avg_cc, bug |
| Bug prediction dataset | 24 | 5371 | classname ,  cbo ,  dit ,  fanIn ,  fanOut ,  lcom ,  noc ,  numberOfAttributes ,  numberOfAttributesInherited ,  numberOfLinesOfCode ,  numberOfMethods ,  numberOfMethodsInherited ,  numberOfPrivateAttributes ,  numberOfPrivateMethods ,  numberOfPublicAttributes ,  numberOfPublicMethods ,  rfc ,  wmc ,  bugs ,  nonTrivialBugs ,  majorBugs ,  criticalBugs ,  highPriorityBugs |
| **Skupno obema** | 9 | 93.122 | cbo, dit, lcom, noc, loc, npm, rfc, wmc, bug(s) | 

Pogojno še lahko izračunamo DAM (data access metric) v *Bug prediction dataset-u* in imamo 10 skupnih atributov.
