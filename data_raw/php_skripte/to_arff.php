<?php

$arffHeader = "@RELATION change_classic_combined

@ATTRIBUTE numberOfVersionsUntil  NUMERIC
@ATTRIBUTE numberOfFixesUntil   NUMERIC
@ATTRIBUTE numberOfRefactoringsUntil  NUMERIC
@ATTRIBUTE umberOfAuthorsUntil   NUMERIC
@ATTRIBUTE linesAddedUntil   NUMERIC
@ATTRIBUTE maxLinesAddedUntil   NUMERIC
@ATTRIBUTE avgLinesAddedUntil   NUMERIC
@ATTRIBUTE linesRemovedUntil   NUMERIC
@ATTRIBUTE maxLinesRemovedUntil   NUMERIC
@ATTRIBUTE avgLinesRemovedUntil   NUMERIC
@ATTRIBUTE codeChurnUntil   NUMERIC
@ATTRIBUTE maxCodeChurnUntil   NUMERIC
@ATTRIBUTE avgCodeChurnUntil   NUMERIC
@ATTRIBUTE weightedAgeWithRespectTo   NUMERIC
@ATTRIBUTE ageWithRespectTo   NUMERIC
@ATTRIBUTE cbo  NUMERIC
@ATTRIBUTE dit  NUMERIC
@ATTRIBUTE fanIn  NUMERIC
@ATTRIBUTE fanOut  NUMERIC
@ATTRIBUTE lcom  NUMERIC
@ATTRIBUTE noc  NUMERIC
@ATTRIBUTE numberOfAttributes  NUMERIC
@ATTRIBUTE numberOfAttributesInherited  NUMERIC
@ATTRIBUTE numberOfLinesOfCode  NUMERIC
@ATTRIBUTE numberOfMethods  NUMERIC
@ATTRIBUTE numberOfMethodsInherited  NUMERIC
@ATTRIBUTE numberOfPrivateAttributes  NUMERIC
@ATTRIBUTE numberOfPrivateMethods  NUMERIC
@ATTRIBUTE numberOfPublicAttributes  NUMERIC
@ATTRIBUTE numberOfPublicMethods  NUMERIC
@ATTRIBUTE rfc  NUMERIC
@ATTRIBUTE wmc  NUMERIC
@ATTRIBUTE bug   {0, 1}

@DATA
";

$servername = "localhost";
$username = "root";
$password = "";
$dbName = "mag";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "
SELECT 
	cm.`numberOfVersionsUntil:` as numberOfVersionsUntil,
	cm.`numberOfFixesUntil:` as numberOfFixesUntil,
	cm.`numberOfRefactoringsUntil:` as numberOfRefactoringsUntil,
	cm.`numberOfAuthorsUntil:` as numberOfAuthorsUntil,
	cm.`linesAddedUntil:` as linesAddedUntil,
	cm.`maxLinesAddedUntil:` as maxLinesAddedUntil,
	cm.`avgLinesAddedUntil:` as avgLinesAddedUntil,
	cm.`linesRemovedUntil:` as linesRemovedUntil,
	cm.`maxLinesRemovedUntil:` as maxLinesRemovedUntil,
	cm.`avgLinesRemovedUntil:` as avgLinesRemovedUntil,
	cm.`codeChurnUntil:` as codeChurnUntil,
	cm.`maxCodeChurnUntil:` as maxCodeChurnUntil,
    cm.`avgCodeChurnUntil:` as avgCodeChurnUntil,
    cm.`weightedAgeWithRespectTo:` as weightedAgeWithRespectTo,
	cm.`ageWithRespectTo:`  as ageWithRespectTo,	
	cl.cbo,
	cl.dit,
	cl.fanIn,
	cl.fanOut,
	cl.lcom,
	cl.noc,
	cl.numberOfAttributes,
	cl.numberOfAttributesInherited,
	cl.numberOfLinesOfCode,
	cl.numberOfMethods,
	cl.numberOfMethodsInherited,
	cl.numberOfPrivateAttributes,
	cl.numberOfPrivateMethods,
	cl.numberOfPublicAttributes,
	cl.numberOfPublicMethods,
	cl.rfc,
	cl.wmc,
	IF(cm.bugs > 0, 1, 0) as bug
FROM
	change_metrics cm,
	classic_metrics cl
WHERE
	cm.classname = cl.classname AND
	cm.project = cl.project AND
    cm.bugs = cl.bugs
";
$fn = "all_combined";
if(isset($_GET["project"])){
    $sql .= " AND cm.project = \"".$_GET["project"]."\"";
    $fn.="_".$_GET["project"];
}
$fn.=".arff";

$result = $conn->query($sql);

if ($result->num_rows > 0) {

    // output data of each row
    $str = "";
    while($row = $result->fetch_assoc()) {        
        $tstr = "";
        foreach($row as $item){
            $tstr .= $item . ", ";
        }
        $str .= substr($tstr, 0, -2)."\n";
    }
    file_put_contents($fn, $arffHeader.$str);
} else {
    echo "0 results";
}

$conn->close();
