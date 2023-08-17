module Region ( Region, newR{- foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR-} )
   where
import Point
import City
import Quality
import Link
import Tunel

--LA PARTE DEL DERIVING NO ESTA EN EL ENUNCIADO, SACAR
data Region = Reg [City] [Link] [Tunel] deriving(Show)
--LA PARTE DEL DERIVING NO ESTA EN EL ENUNCIADO, SACAR

newR :: Region
newR = Reg [olivos,mtz,berazategui,munro] [sanIsidro,otroMunicipio] [tunelLaNoria]

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
-}

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (connectsT city1 city2) tunels)


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (linksL city1 city2) links)

{-
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
-}

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
--Regions
--newR = Reg [olivos,mtz] [sanIsidro,otroMunicipio] [tunelLaNoria]
--nuevoR = foundR newR berazategui