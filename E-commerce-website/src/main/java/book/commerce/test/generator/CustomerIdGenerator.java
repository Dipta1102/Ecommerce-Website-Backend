package book.commerce.test.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

public class CustomerIdGenerator implements Configurable, IdentifierGenerator {

	private static long ID = 1L;

	private String getCustomerId() {
		final String customerId = "BWCUST1908" + ID++;
		return customerId;
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		return this.getCustomerId();
	}

}
