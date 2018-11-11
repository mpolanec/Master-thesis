param(
    [String]$Filter, # filter
    [String]$HeaderPath # path to header file
)

$headerText = Get-Content $HeaderPath

Get-ChildItem -Filter $Filter -Recurse -File | ForEach-Object {
    
    # 5. preberi vse ostale vrstice (brez glave)
    $content = Get-content $_.FullName | select-object -skip 1

    #zamenja vsa podpičja z vejicami
    $content = $content -replace ";",","
    $tmp = $headerText -replace "FILE_NAME", $_.BaseName
    $content = $tmp + $content
    Set-Content $_.FullName -Value $content

    #preimenuj datoteko
    Get-Item -Path $_.FullName | Rename-Item -NewName { [io.path]::ChangeExtension($_.name, "arff") }
}
