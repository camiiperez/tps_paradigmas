module Link ( Link, newL,linksL,connectsL,capacityL,delayL) where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link 
newL city1 city2 quality
                | (nameC(city1) == nameC(city2)) = error("No se puede generar un link entre una misma ciudad")
                | otherwise = Lin city1 city2 quality

connectsL :: City -> Link -> Bool 
connectsL city (Lin city1 city2 quality) =  elem (nameC(city)) [nameC(city1),nameC(city2)]

isCityInList :: City -> [City] -> Bool
isCityInList citySearched [city1,city2] = elem (nameC(citySearched)) [nameC(city1),nameC(city2)] 

linksL :: City -> City -> Link -> Bool 
linksL city1 city2 (Lin city1' city2' quality)
                    | (nameC(city1) == nameC(city2)) = error "Las dos ciudades proporcionadas deben ser distintas"
                    | otherwise = isCityInList city1 [city1',city2'] && isCityInList city2 [city1',city2'] 

capacityL :: Link -> Int
capacityL (Lin city1 city2 quality) = capacityQ(quality)

delayL :: Link -> Float
delayL (Lin city1 city2 quality) = distanceC city1 city2 * delayQ(quality)
