package it.tbridge.maptest.bean;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.tbridge.maptest.entity.AddressElement;
import it.tbridge.maptest.entity.AddressElement.AddressComponentType;
import it.tbridge.maptest.entity.GeoPoint;
import it.tbridge.maptest.entity.GeographicLocation;
import it.tbridge.maptest.entity.GeographicLocation_;

/**
 * Session Bean implementation class GeographicLocationBean
 */
@Stateless
@LocalBean
public class GeographicLocationBean {

	/**
	 * Default constructor.
	 */
	public GeographicLocationBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(name = "GoogleMapsPU")
	private EntityManager em;

	public List<GeographicLocation> findByStartPoint() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<GeographicLocation> q = cb.createQuery(GeographicLocation.class);
		Root<GeographicLocation> a = q.from(GeographicLocation.class);
		q.where(cb.equal(a.get(GeographicLocation_.startPoint), true));
		return this.em.createQuery(q).getResultList();
	}

	public List<GeographicLocation> findByEndPoint() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<GeographicLocation> q = cb.createQuery(GeographicLocation.class);
		Root<GeographicLocation> a = q.from(GeographicLocation.class);
		q.where(cb.equal(a.get(GeographicLocation_.endPoint), true));
		return this.em.createQuery(q).getResultList();
	}

	public List<GeographicLocation> findByWaytPoint() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<GeographicLocation> q = cb.createQuery(GeographicLocation.class);
		Root<GeographicLocation> a = q.from(GeographicLocation.class);
		q.where(cb.equal(a.get(GeographicLocation_.wayPoint), true));
		return this.em.createQuery(q).getResultList();
	}

	public GeographicLocation save(GeographicLocation item) {
		if (item != null) {
			if (item.getId() == null) {
				create(item);
			} else {
				update(item);
			}
		}
		return item;
	}

	private GeographicLocation create(GeographicLocation item) {
		save(item.getBoxNEPoint());
		save(item.getBoxSWPoint());
		save(item.getLocationPoint());
		save(item.getAddressData());
		em.persist(item);
		return item;
	}

	private GeographicLocation update(GeographicLocation item) {
		save(item.getBoxNEPoint());
		save(item.getBoxSWPoint());
		save(item.getLocationPoint());
		save(item.getAddressData());
		em.merge(item);
		return item;
	}

	public GeoPoint save(GeoPoint item) {
		GeoPoint ret = null;
		if (item != null) {
			if (item.getId() == null) {
				ret = create(item);
			} else {
				ret = update(item);
			}
		}
		return ret;

	}

	private GeoPoint create(GeoPoint item) {
		em.persist(item);
		return item;

	}

	private GeoPoint update(GeoPoint item) {
		em.merge(item);
		return item;
	}

	public Map<AddressComponentType, AddressElement> save(Map<AddressComponentType, AddressElement> addressData) {
		if (addressData != null) {
			Set<AddressComponentType> key = addressData.keySet();
			for (AddressComponentType k : key) {
				AddressElement ae = addressData.get(k);
				save(ae);
			}
		}
		return addressData;
	}

	public AddressElement save(AddressElement item) {
		AddressElement ret = null;
		if (item != null) {
			if (item.getId() == null) {
				ret = create(item);
			} else {
				ret = update(item);
			}
		}
		return ret;

	}

	private AddressElement create(AddressElement item) {
		em.persist(item);
		return item;
	}

	private AddressElement update(AddressElement item) {
		em.merge(item);
		return item;
	}

}
