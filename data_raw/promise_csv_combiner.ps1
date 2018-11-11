param(
    [String]$Filter="*.csv" # filter
)

$commonAttributes = "wmc,dit,noc,cbo,rfc,lcom,ca,ce,npm,lcom3,loc,dam,moa,mfa,cam,ic,cbm,amc,max_cc,avg_cc,bug"
$commonAttributesArr = $commonAttributes.Split(",")

# ustvari nov direktorij s skupno datoteko

New-Item -Path "." -Name "All" -ItemType "directory" | Out-Null
Set-Location "All"
New-Item -Path "." -Name "all.csv1" -ItemType "file" | Out-Null
Add-Content "all.csv1" -Value $commonAttributes
Set-Location ".."
$allDataPath = "./All/all.csv1"

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
            if($useIndexes.Contains($i)){
                if($i -eq $aVals.Length - 1){
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
