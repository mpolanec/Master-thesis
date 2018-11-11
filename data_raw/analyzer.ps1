param(
    [String]$Filter="*.csv" # filter
)

Write-Output "Zacetek analize"

$sumNbInstaces = 0;
$minAttr = 9999;
$maxAttr = 0;
$sumAttr = 0;
$nfiles = 0;

Get-ChildItem -Filter $Filter -Recurse -File | ForEach-Object {
    $nfiles++;

    $fileInfo =  Get-Content $_.FullName | Measure-Object -Line
    $nbInstances = $fileInfo.Lines - 1
    [string]$firstLine = Get-Content $_.FullName -Head 1
    [string]$separator = ","
    if($firstLine.Contains(";")){
        $separator = ";"
    }
    $nbOfAttr = $firstLine.Split($separator).Count

    $sumAttr += $nbOfAttr
    if($nbOfAttr -lt $minAttr){
        $minAttr = $nbOfAttr
    }

    if($nbOfAttr -gt $maxAttr){
        $maxAttr = $nbOfAttr
    }

    $sumNbInstaces = $sumNbInstaces + $nbInstances;

    Write-Output "| $($_.Directory.Name)/$($_.Name) | $($nbOfAttr) | $($nbInstances) |"
}

Write-Output ""
Write-Output "Skupaj instanc: **$($sumNbInstaces)**"
Write-Output ""
Write-Output "Minimalno st. atributov: **$($minAttr)**"
Write-Output ""
Write-Output "Povprecno st. atributov: **$($sumAttr/$nfiles)**"
Write-Output ""
Write-Output "Maksimalno st. atributov: **$($maxAttr)**"