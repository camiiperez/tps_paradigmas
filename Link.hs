module Link ( Link, newL,linksL,connectsL,capacityL,delayL) where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)
--
newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 quality) =  elem (nameC(city)) [nameC(city1),nameC(city2)]

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 (Lin city1' city2' quality) = elem (nameC(city1)) [nameC(city1'),nameC(city2')] && elem (nameC(city2)) [nameC(city1'),nameC(city2')]
   
capacityL :: Link -> Int
capacityL (Lin city1 city2 quality) = capacityQ(quality)

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = delayQ(quality)

-------------

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
  

