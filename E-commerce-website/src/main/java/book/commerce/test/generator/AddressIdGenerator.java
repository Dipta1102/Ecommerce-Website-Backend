package book.commerce.test.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

public class AddressIdGenerator implements IdentifierGenerator, Configurable {

	private static long ID = 1L;

	private String getAddressId() {
		final String addressId = "BWADDR1908" + ID++;
		return addressId;
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		return this.getAddressId();
	}

}
