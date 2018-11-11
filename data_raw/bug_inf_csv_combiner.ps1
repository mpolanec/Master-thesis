param(
    [String]$Filter="*.csv", # filter
    [int]$mode = 1
)

$commonAttributes = "cbo;dit;fanIn;fanOut;lcom;noc;numberOfAttributes;numberOfAttributesInherited;numberOfLinesOfCode;numberOfMethods;numberOfMethodsInherited;numberOfPrivateAttributes;numberOfPrivateMethods;numberOfPublicAttributes;numberOfPublicMethods;rfc;wmc;bugs"
if($mode -ne 1){
    $commonAttributes = "numberOfVersionsUntil:;numberOfFixesUntil:;numberOfRefactoringsUntil:;numberOfAuthorsUntil:;linesAddedUntil:;maxLinesAddedUntil:;avgLinesAddedUntil:;linesRemovedUntil:;maxLinesRemovedUntil:;avgLinesRemovedUntil:;codeChurnUntil:;maxCodeChurnUntil:;avgCodeChurnUntil:;ageWithRespectTo:;weightedAgeWithRespectTo:;bugs";
}
$commonAttributesArr = $commonAttributes.Split(";")

# ustvari nov direktorij s skupno datoteko

if(!(Test-Path -Path "All" )){
    New-Item -Path "." -Name "All" -ItemType "directory" | Out-Null
}
Set-Location "All"
New-Item -Path "." -Name ("all"+($Filter -replace ".csv", "")+".csv1") -ItemType "file" | Out-Null
Add-Content ("all"+($Filter -replace ".csv", "")+".csv1") -Value $commonAttributes
Set-Location ".."
$allDataPath = "./All/all"+($Filter -replace ".csv", "")+".csv1"

# pregledamo glavo vsake datoteke, kjer poiščemo lokacije (indekse) posameznih atributov
# atribute združujemo v eno datoteko
# prav tako vsako datoteko pretvorimo, da jo lahko kasneje uporabimo z weka knjižnjico direktno
# vsi numerični atributi uporabljajo decimalno ., ločilo med atributi bo vedno ;
Get-ChildItem -Filter $Filter -Recurse -File | ForEach-Object {
    # 1. pridobi glavo datoteke
    [string]$firstLine = Get-Content $_.FullName -Head 1

    # 2. določi kakšno ločilo se uporablja med podatki
    [string]$separator = ","
    if($firstLine.Contains(";"))
    {
        $separator = ";"
    }

    # 3. določi atribute, ki se bodo uporabljaji še naprej
    $attributes = $firstLine.Split($separator)
    [System.Collections.Generic.List[int]] $useIndexes = New-Object System.Collections.Generic.List[int]

    [int]$i = 0;
    foreach ($a in $attributes){
        $a = $a.Trim();
        if($commonAttributesArr.Contains($a)){
            $useIndexes.Add($i);
        }
        $i++;
    }

    # 4. ustvari datoteko [original]_prepared.[original_koncnica]
    $newName = $_.BaseName + "_prepared" + $_.Extension + "1";
    $newPath = $_.DirectoryName + "\" + $newName

    New-Item $newPath | Out-Null
    Add-Content -LiteralPath $newPath -Value $commonAttributes

    # 5. preberi vse ostale vrstice (brez glave)
    $content = Get-content $_.FullName | select-object -skip 1

    # 6. sprocesiraj vse vrstice in jih dopiši v novo datoteko
    foreach($c in $content){
        $newLine = ""
        if($separator -eq ";"){
            # če se je prej kot separator uporabljalo podpičje, zamenjam morebitne vejice z pikami
            $c = $c -replace ",", "."
        }
        $aVals = $c.Split($separator);
        $i = 0;
        foreach ($a in $aVals){
            $a = $a.Trim()
            if($useIndexes.Contains($i)){
                if($i -eq $useIndexes.Count){
                    $tmp = [int]$a;
                    if($tmp -gt 0){
                        $a = "1";
                    }
                }
                $newLine += $a + ";";
            }
            $i++;
        }
        #odstrani zadnje podpičje
        $newLine = $newLine -replace ";$", ""
        # dodaj v datoteko
        Add-Content -LiteralPath $newPath -Value $newLine
        Add-Content -LiteralPath $allDataPath -Value $newLine
    }
}
# 7. preimenuj vse datoteke
Get-ChildItem *.csv1 -R | rename-item -newname { [io.path]::ChangeExtension($_.name, "csv") }
