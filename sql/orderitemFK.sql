use Fithub
ALTER TABLE orderitem
ADD CONSTRAINT fk_Coupon_Id FOREIGN KEY (couponid) REFERENCES coupon(couponid);