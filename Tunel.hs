module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linksList = Tun linksList

esExtremo :: [Link] -> City -> Bool
esExtremo linksList city 
   |length([link | link <- linksList, connectsL city link]) > 1 = False
   |otherwise = True

connectsT :: City -> City -> Tunel -> Bool 
connectsT city1 city2 (Tun linksList) =  (esExtremo linksList city1) && (esExtremo linksList city2)

usesT :: Link -> Tunel -> Bool 
usesT linkSearched (Tun linksList) = elem linkSearched linksList

delaySum :: [Float] -> Float
delaySum [] = 0
delaySum (x:xs) = x + delaySum(xs)

delayT :: Tunel -> Float
delayT (Tun linksList) = delaySum ([delayL(x) | x <- linksList])

