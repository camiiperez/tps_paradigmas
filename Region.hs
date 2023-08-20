module Region ( Region, newR{- foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR-} )
   where
import Point
import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving(Show)

newR :: Region
newR = Reg [olivos,mtz,berazategui,munro,merlo,sanluis] [sanIsidro,otroMunicipio,provincia] [tunelLaNoria,tunelAvellaneda,tunelB,tunelC,tunelD]

bindCities :: [City] -> [City] -> [City]
bindCities city1 city2 = city1 ++ city2

bindLinks :: [Link] -> [Link] -> [Link]
bindLinks links1 links2 = links1 ++ links2

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city = (Reg (bindCities cities [city] ) links tunels)


linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality = Reg cities (bindLinks[newL city1 city2 quality] links) tunels

{-
tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) citiesList = Reg
-}

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (connectsT city1 city2) tunels)


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (linksL city1 city2) links)

getConnectedTunels :: City -> City -> [Tunel] -> [Tunel]
getConnectedTunels city1 city2 tunels = foldr accumulate [] tunels
    where accumulate tunel acc = if connectsT city1 city2 tunel then tunel : acc else acc

getTunelInsideList :: [Tunel] -> Tunel
getTunelInsideList [tunel] = tunel


delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunels) city1 city2 = if (foldr (||) False (map (connectsT city1 city2) tunels)) then  delayT(getTunelInsideList(getConnectedTunels city1 city2 tunels)) else error "Las ciudades no estan conectadas"

howManyLinksInTunel :: [Tunel] -> Link -> Int
howManyLinksInTunel tunelsList link = sum (map (\tunnel -> if usesT link tunnel then 1 else 0) tunelsList)  

whichLinkConnects :: City -> City -> [Link] -> Link
whichLinkConnects city1 city2 linksList = head [link | link <- linksList, linksL city1 city2 link]



availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cities links tunels) city1 city2 = if (foldr (||) False (map (linksL city1 city2) links))
    then (capacityL (whichLinkConnects city1 city2 links) ) - (howManyLinksInTunel tunels (whichLinkConnects city1 city2 links)) 
    else error "Las ciudades no estan enlazadas (por un link), o no hay mas capacidad"


--Cities
olivos = newC "Olivos" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)
berazategui = newC "Berazategui" (newP 8 4)
munro = newC "Munro" (newP 5 6)
merlo = newC "Merlo" (newP 19 8)
sanluis = newC "San Luis" (newP 15 9)
--Qualities
fibraOptica = newQ "Fibra Optica" 4 22.3
cableNormal = newQ "Cable" 2 15.4
--Links
sanIsidro = newL olivos mtz fibraOptica
otroMunicipio = newL berazategui munro cableNormal
provincia = newL merlo sanluis cableNormal
--Tuneles
tunelLaNoria= newT [sanIsidro,otroMunicipio]
tunelAvellaneda = newT [sanIsidro, otroMunicipio, provincia]
tunelB = newT [sanIsidro,provincia,otroMunicipio]
tunelC = newT [sanIsidro,provincia,otroMunicipio]
tunelD = newT [sanIsidro,otroMunicipio]
--Regions
--newR = Reg [olivos,mtz] [sanIsidro,otroMunicipio] [tunelLaNoria]
--nuevoR = foundR newR berazategui