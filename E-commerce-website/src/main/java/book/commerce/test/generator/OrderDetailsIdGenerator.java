package book.commerce.test.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

public class OrderDetailsIdGenerator implements Configurable, IdentifierGenerator {

	private static long ID = 1L;

	private String getOrderDetailsId() {
		final String orderDetailsId = "BWORDTLS1908" + ID++;
		return orderDetailsId;
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		return this.getOrderDetailsId();
	}

}
