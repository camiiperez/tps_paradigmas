module Tunel ( Tunel, newT, {-connectsT, usesT, delayT-} )
   where

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linksList = Tun linksList
  
citiesInTunels :: Tunel -> [String]
citiesInTunels (Tun [link1,link2]) = (cityNamesInLinks(link1) ++ cityNamesInLinks(link2))

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = (elem (nameC(city1)) (citiesInTunels(tunel))) && (elem (nameC(city2)) (citiesInTunels(tunel)))

areCitiesInTunnel :: [City] -> Tunel -> Bool
areCitiesInTunnel [city1,city2] tunel = (elem (nameC(city1)) (citiesInTunels(tunel))) && (elem (nameC(city2)) (citiesInTunels(tunel)))  


usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link tunel = areCitiesInTunnel (citiesTypeInLinks(link)) tunel

{-
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
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