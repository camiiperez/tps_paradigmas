module Tunel ( Tunel, newT, {-connectsT, usesT, delayT-} )
   where

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linksList = Tun linksList

linkNamesInTunels :: Tunel -> [Link]
linkNamesInTunels (Tun [link1,link2]) = [link1,link2]

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
{-
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
-}

--Cities
olivos = newC "Olivos" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)
berazategui = newC "Berazategui" (newP 8 4)
munro = newC "Munro" (newP 5 6)
--Qualities
fibraOptica = newQ "Fibra Optica" 4 22.3
cableNormal = newQ "Cable" 2 15.4
--Links
sanIsidro = newL olivos mtz fibraOptica
otroMunicipio = newL berazategui munro cableNormal
--Tuneles
tunelLaNoria= newT [sanIsidro,otroMunicipio]